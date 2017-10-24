Transcoding to HLS service implemented using java Spring Boot and
AWS services (EC2, S3, RDB, Elastic Transcoder, AWS Lambda, SNS, SQS)

1. Enter point:
http://ec2-34-215-102-121.us-west-2.compute.amazonaws.com

login with test user:
un: levon
pw: password

Spring security is set.

2. Upload to s3 is implemented using javascript using accessKeyId and secretAccessKey.
If choosed video length is more than 10 min, then appropriate alert will appear and choosen
file name will be removed.

TRADE-OFF: although credentials are viewable only to authorized users (upload.html), ideally,
would be secure to upload through the backend which will cause extra load and time.
For frontend uploading to S3 more research could be done on encoding credentials and possibly
engaging AWS services for decoding.

3. on AWS elastic transcoder is configured, lambda function is set to trigger transcoder
upon video file upload to the input bucket. Trans-coded file is appeared in the output bucket.

4. Transcoding progress updates are come through Elastic Transcoder -> SNS -> SQS.
SQS listener catch the messages (JSON object string) and stores them in static variable
jsonTranscodingStatus.

5. Javascript event fetches for jsonTranscodingStatus every 3 seconds and updates frontend
with status info:

 File uploaded successfully.
 State: COMPLETED
 Duration: 6 min
 Download: hls-tQAA8oEN5_SampleVideo_720x480_1mb.ts (downloadable link)
 Resolution: 640x480

 Before COMPLETED user will see other status info informing about trans-coding progress.


COMMENTS:
Solution implemented with both RDB and local Mysql on Ubuntu EC2 instance.
Currently, local Mysql is set as an active option in application.properties


API:

/transcoding.status - saves trans-coding status info from SQSListener
/login - authorise user using Spring Security
/upload - opens page for s3 uplaoding
