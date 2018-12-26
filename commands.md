## SSH 
<pre>
ssh -L 9000:imgur.com:80 user@example.com
</pre>

-L local port forwarding. 
Forwarding our local port 9000 to imgur.com:80

<pre>
ssh -L 9000:localhost:5432 user@example.com
</pre>
Forward connections from your local port 9000 to localhost:5432 on your server. 

<pre>
ssh-keygen
</pre>
<pre>
ssh-keygen -t rsa
</pre>
to create a key-pair. public key can be shared. By default the key type is rsa.


## Curl
<pre>
curl www.google.dk 
curl -u user:password http://example.org/
</pre>

<pre>
curl -o output.html http://example.com/
</pre>
Output the response to output.html

<pre>
curl --max-time 5.5 https://example.com/
</pre>
Timeout 5500 milis

<pre>
curl --data "birthyear=1905&press=%20OK%20"  http://www.example.com/when.cgi
curl --data-urlencode "name=Hao Deng" http://www.example.com
</pre>

<pre>
curl -w "\n" --header "Content-Type: text/xml;charset=UTF-8" http://www.example.com --data @example.xml
</pre>
Add headers, take the local file example.xml and send to server.

<pre>
curl -w "Type: %{content_type}\nCode: %{response_code}\n" http://example.com
</pre>
Write out the response

<pre>
curl -H "Authorization: Basic dGVzdDE6dGVzdDI=" "http://example.com/web/test/status?reference=400003A13C0671607251525040&status=delivered"
</pre>
Authorization
