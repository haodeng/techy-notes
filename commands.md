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
to create a key-pair. public key can be shared.
