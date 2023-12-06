
import express,{Express,Request,Response} from 'express';
import mongoose, { Model } from 'mongoose';
import cors from 'cors';
import moment from 'moment';
import http from 'http';
const app:Express = express();
const port = 3000;
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(cors)

main().catch((err) => console.log(err));
async function main() {
  await mongoose.connect('mongodb://localhost/ads');
  console.log('Database connected!');
}
const reportSchema = new mongoose.Schema({
  userId: String,
  adId: String,
  reason: String,
  description: String,
  date: Date,
});
const report = mongoose.model('report', reportSchema);

app.post('/addReport',async (req:Request, res:Response) => {
  const details = req.body;
  const date:Date = new Date();
  const now = moment(date).format('HH:mm:ss');
  const sampleReport = new report({
    userId: details.userId,
    adId: details.adId,
    reason: details.reason,
    description: details.description,
    date: now,
  });
  try {
    const savedReport = await sampleReport.save();
    res.status(200).json(savedReport);
  } catch (err:any) {
    res.status(500).send('server error please contact the admin');
  }

});
app.get('/getReports',async (req:Request, res:Response) => {
  try {
    const reports = await report.find();
    const reportWithAds: any = await Promise.all(reports.map(async (report) => {
      const ad = await http.get(`http://localhost:8081/ads/${report.adId}`);
      return { report: report, ad: ad };
    }));
    res.status(200).json(reportWithAds);
  } catch (err:any) {
    res.status(500).json('server error please contact the admin');
  }
});
app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
app.delete('/deleteReport/:id',async (req:Request, res:Response) => {
  try {
    const response = req.body.response;
    if(response == "delete"){
      const rep = await report.findById(req.params.id);
      const options = {
        hostname: 'http://localhost',
        port: 8081,
        path: "/deleteAd" + req.params.id,
        method: 'DELETE',
    };
    let resStatus:number|undefined = 0
      await http.request(options,(res)=>{
        resStatus = res.statusCode;
      });
      if(resStatus == 200){
        await report.deleteOne();
        res.status(200).json('report deleted');
      }
      else{
        res.status(500).json('server error please contact the admin');
      }
    }
  } catch (err:any) {
    res.status(500).json('server error please contact the admin');
  }
});
