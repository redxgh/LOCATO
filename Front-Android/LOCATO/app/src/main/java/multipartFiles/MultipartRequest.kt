package multipartFiles

import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.HttpHeaderParser
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.net.URLConnection

class MultipartRequest(
    method: Int,
    url: String,
    private val fileParts: Map<String, File>,
    private val stringParts: Map<String, String>,
    private val listener: Response.Listener<NetworkResponse>,
    private val errorListener: Response.ErrorListener
) : Request<NetworkResponse>(method, url, errorListener) {

    private val boundary = "----WebKitFormBoundary7MA4YWxkTrZu0gW"

    init {
        setRetryPolicy(DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT))
        setShouldCache(false)
    }

    @Throws(AuthFailureError::class)
    override fun getBody(): ByteArray {
        val bos = ByteArrayOutputStream()
        val dos = DataOutputStream(bos)
        try {
            // Adding String data
            for ((key, value) in stringParts) {
                dos.writeBytes("--$boundary\r\n")
                dos.writeBytes("Content-Disposition: form-data; name=\"$key\"\r\n\r\n")
                dos.writeBytes("$value\r\n")
            }

            // Adding File data
            for ((key, file) in fileParts) {
                val fileInputStream = FileInputStream(file)
                dos.writeBytes("--$boundary\r\n")
                dos.writeBytes("Content-Disposition: form-data; name=\"$key\"; filename=\"${file.name}\"\r\n")
                dos.writeBytes("Content-Type: ${URLConnection.guessContentTypeFromName(file.name)}\r\n\r\n")

                var bytesAvailable = fileInputStream.available()
                var bufferSize = 1024
                val buffer = ByteArray(bufferSize)
                var bytesRead = fileInputStream.read(buffer, 0, bufferSize)

                while (bytesRead > 0) {
                    dos.write(buffer, 0, bufferSize)
                    bytesAvailable = fileInputStream.available()
                    bufferSize = Math.min(bytesAvailable, 1024)
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize)
                }

                dos.writeBytes("\r\n")
            }

            // End of multipart/form-data
            dos.writeBytes("--$boundary--\r\n")
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return bos.toByteArray()
    }

    override fun getBodyContentType(): String {
        return "multipart/form-data; boundary=$boundary"
    }

    override fun parseNetworkResponse(response: NetworkResponse): Response<NetworkResponse> {
        return Response.success(response, HttpHeaderParser.parseCacheHeaders(response))
    }

    override fun deliverResponse(response: NetworkResponse) {
        listener.onResponse(response)
    }

    override fun deliverError(error: VolleyError) {
        errorListener.onErrorResponse(error)
    }
}
