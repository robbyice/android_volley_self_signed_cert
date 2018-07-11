const https = require('https');
const fs = require('fs');

process.env['NODE_TLS_REJECT_UNAUTHORIZED'] = 0;

const options = {
    key: fs.readFileSync('client-key.pem'),
    cert: fs.readFileSync('client-cert.pem'),
    passphrase: 'foobar'
};

const server = https.createServer(options, (req, resp) => {
    console.log("Request received");
    resp.writeHead(200);
    resp.end('Hello world\n');
}).listen(8000);
