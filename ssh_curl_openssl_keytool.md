## SSH 
-L local port forwarding. 
Forwarding our local port 9000 to imgur.com:80

    ssh -L 9000:imgur.com:80 user@example.com

Forward connections from your local port 9000 to localhost:5432 on your server. 

    ssh -L 9000:localhost:5432 user@example.com

Local forward use ssh key

    ssh -i /path/to/keyfile -L 9009:10.0.0.1:3306 user@host


to create a key-pair. public key can be shared. By default the key type is rsa.

    ssh-keygen
    ssh-keygen -t rsa

-p' Requests changing the passphrase of a private key file instead of creating a new private key. The program will prompt for the file containing the private key, for the old passphrase, and twice for the new passphrase.

    ssh-keygen -p -f /path/to/private.key

ssh-add is a command for adding SSH private keys into the SSH authentication agent for implementing single sign-on with SSH

    ssh-add -K /path/to/private.key


## Curl

    curl www.google.dk 
    curl -u user:password http://example.org/

Output the response to output.html

    curl -o output.html http://example.com/

Timeout 5500 milis

    curl --max-time 5.5 https://example.com/


    curl --data "birthyear=1905&press=%20OK%20"  http://www.example.com/when.cgi
    curl --data-urlencode "name=Hao Deng" http://www.example.com


Add headers, take the local file example.xml and send to server.

    curl -w "\n" --header "Content-Type: text/xml;charset=UTF-8" http://www.example.com --data @example.xml


Write out the response

    curl -w "Type: %{content_type}\nCode: %{response_code}\n" http://example.com

Authorization

    curl -H "Authorization: Basic dGVzdDE6dGVzdDI=" "http://example.com/web/test/status?reference=400003A13C0671607251525040&status=delivered"


Visit insecure urls

    curl -k https://incsure.xx.com


## Openssl 
View the certificate info in text

    openssl x509 -text -inform DER -in 1.cer
    openssl x509 -text -inform PEM -in 1.pem



## Keytool
The default keystore password is: changeit

    keytool -import -alias name -file my.cer -keystore cacerts
 
 
    keytool -list -keystore cacerts|grep name
