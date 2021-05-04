# signs the file named MyControl.exe using a certificate stored in a Personal Information Exchange (PFX) file

    SignTool sign /f MyCert.pfx MyControl.exe

Location of signtool: C:\Program Files (x86)\Windows Kits\10\bin\10.0.19041.0\x64\signtool.exe


# signs the file using a certificate located in the My store with a subject name of My Company Publisher
    
    SignTool sign /n "My Company Publisher" MyControl.exe

Check the cerfiticates installed by windows "Manage computer certificates"
