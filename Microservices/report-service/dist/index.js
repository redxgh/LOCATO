"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const mongoose_1 = __importDefault(require("mongoose"));
const cors_1 = __importDefault(require("cors"));
const moment_1 = __importDefault(require("moment"));
const http_1 = __importDefault(require("http"));
const app = (0, express_1.default)();
const port = 3000;
app.use(express_1.default.json());
app.use(express_1.default.urlencoded({ extended: true }));
app.use(cors_1.default);
main().catch((err) => console.log(err));
function main() {
    return __awaiter(this, void 0, void 0, function* () {
        yield mongoose_1.default.connect('mongodb://localhost/ads');
        console.log('Database connected!');
    });
}
const reportSchema = new mongoose_1.default.Schema({
    userId: String,
    adId: String,
    reason: String,
    description: String,
    date: Date,
});
const report = mongoose_1.default.model('report', reportSchema);
app.post('/addReport', (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const details = req.body;
    const date = new Date();
    const now = (0, moment_1.default)(date).format('HH:mm:ss');
    const sampleReport = new report({
        userId: details.userId,
        adId: details.adId,
        reason: details.reason,
        description: details.description,
        date: now,
    });
    try {
        const savedReport = yield sampleReport.save();
        res.status(200).json(savedReport);
    }
    catch (err) {
        res.status(500).send('server error please contact the admin');
    }
}));
app.get('/getReports', (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    try {
        const reports = yield report.find();
        const reportWithAds = yield Promise.all(reports.map((report) => __awaiter(void 0, void 0, void 0, function* () {
            const ad = yield http_1.default.get(`http://localhost:8081/ads/${report.adId}`);
            return { report: report, ad: ad };
        })));
        res.status(200).json(reportWithAds);
    }
    catch (err) {
        res.status(500).json('server error please contact the admin');
    }
}));
app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
});
app.delete('/deleteReport/:id', (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    try {
        const response = req.body.response;
        if (response == "delete") {
            const rep = yield report.findById(req.params.id);
            const options = {
                hostname: 'http://localhost',
                port: 8081,
                path: "/deleteAd" + req.params.id,
                method: 'DELETE',
            };
            let resStatus = 0;
            yield http_1.default.request(options, (res) => {
                resStatus = res.statusCode;
            });
            if (resStatus == 200) {
                yield report.deleteOne();
                res.status(200).json('report deleted');
            }
            else {
                res.status(500).json('server error please contact the admin');
            }
        }
    }
    catch (err) {
        res.status(500).json('server error please contact the admin');
    }
}));
