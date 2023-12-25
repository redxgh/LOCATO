const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
const moment = require('moment');
//const admin = require('firebase-admin');
const axios = require('axios');

const app = express();
const port = 3040;
/*admin.initializeApp({
  credential: admin.credential.cert({
    projectId: '<YOUR_PROJECT_ID>',
    clientEmail: '<YOUR_CLIENT_EMAIL>',
    privateKey: 'AAAA0vXgUvQ:APA91bECMcT315EUj1du5kqlJWbuFFBmKNIsAW_qX3r_fhuD_oUYvNvHtKhxkBXyJ7hArWU979Adhay25rygyrmKpWL3NofMKwm4mBNj_slxmV0lfJLMQGL9DUwbEN4Op6soJvlm3Umr',
  }),
  databaseURL: 'https://<YOUR_DATABASE_NAME>.firebaseio.com', // Add your database URL
});*/

/*const messaging = admin.messaging();*/
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(cors());

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

app.post('/addReport', async (req, res) => {
  const details = req.body;
  const date = new Date();
  const sampleReport = new report({
    userId: details.userId,
    adId: details.adId,
    reason: details.reason,
    description: details.description,
    date: date,
  });

  try {
    const savedReport = await sampleReport.save();
    console.log('Saved Report:', details);
    res.status(200).json(savedReport);

    // Send notification to the "admin" topic
    /*const topic = 'admin';
    try {
      const payload = {
        notification: {
          title: 'New Report',
          body: 'A new report has been added',
        },
      };

      await admin.messaging().sendToTopic(topic, payload);
      res.status(200).json({ message: 'Notification sent successfully!' });
    } catch (error) {
      console.error('Error sending notification:', error);
      res.status(500).json({ message: 'Error sending notification!' });
    }*/
  } catch (err) {
    console.log(err);
    res.status(500).send('Server error, please contact the admin');
  }
});


app.post('/sendNotification', async (req, res) => {

});

app.get('/getReports', async (req, res) => {
  try {
    const reports = await report.find();
    res.status(200).json(reports);
  } catch (err) {
    console.error('Error fetching reports:', err);
    res.status(500).json('server error please contact the admin');
  }
});

app.delete('/deleteReport/:id', async (req, res) => {
  try {
    const responseValue = req.body.response;
    const rep = await report.findById(req.params.id);

    if (responseValue === 'delete') {
      // Continue with the deletion logic
      if (rep) {
        // Check if rep is defined before attempting to delete
        const adDeleteResponse = await axios.delete(`http://localhost:8081/deleteAd/${req.params.id}`);

        if (adDeleteResponse.status === 200) {
          await report.deleteOne(rep);
          res.status(200).json({ message: 'Report and associated ad deleted' });
        } else {
          res.status(500).json('Error deleting ad. Please contact the admin.');
        }
      } else {
        res.status(404).json('Report not found');
      }
    } else {
      // Check if rep is defined before attempting to delete
      if (rep) {
        await report.deleteOne(rep);
        res.status(200).json({ message: 'Report deleted' });
      } else {
        res.status(404).json('Report not found');
      }
    }
  } catch (err) {
    console.error('Error:', err);
    res.status(500).json('Server error. Please contact the admin.');
  }
});

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
