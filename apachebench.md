# Apache Bench
By default ApacheBench is installd on Mac.
## Check version
    
    ab -V
    This is ApacheBench, Version 2.3 <$Revision: 1843412 $>
    Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
    Licensed to The Apache Software Foundation, http://www.apache.org/

## Help info
    
    ab -h
    Usage: ab [options] [http[s]://]hostname[:port]/path

## Send 20 requests, 10 concurrent threads

    ab -n 20 -c 10 https://www.apache.org/
    
        Benchmarking www.apache.org (be patient).....done


        Server Software:        Apache
        Server Hostname:        www.apache.org
        Server Port:            443
        SSL/TLS Protocol:       TLSv1.2,ECDHE-RSA-AES256-GCM-SHA384,2048,256
        Server Temp Key:        ECDH X25519 253 bits
        TLS Server Name:        www.apache.org

        Document Path:          /
        Document Length:        87200 bytes

        Concurrency Level:      10
        Time taken for tests:   1.637 seconds
        Complete requests:      20
        Failed requests:        0
        Total transferred:      1750560 bytes
        HTML transferred:       1744000 bytes
        Requests per second:    12.22 [#/sec] (mean)
        Time per request:       818.360 [ms] (mean)
        Time per request:       81.836 [ms] (mean, across all concurrent requests)
        Transfer rate:          1044.49 [Kbytes/sec] received

        Connection Times (ms)
                      min  mean[+/-sd] median   max
        Connect:      274  311  24.3    302     357
        Processing:   264  268   3.7    269     279
        Waiting:       87   89   2.3     88      98
        Total:        544  579  24.1    572     629

        Percentage of the requests served within a certain time (ms)
          50%    572
          66%    585
          75%    598
          80%    607
          90%    623
          95%    629
          98%    629
          99%    629
         100%    629 (longest request)
         
 ## Ploting output to out.data
 
     ab -n 20 -c 10 -g out.data https://www.apache.org/
