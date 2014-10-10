#-*-coding:utf-8-*-
"""
1. python2.7.3
2. pip
3. virtualenv
4. django
"""

import os
import urllib2
import tarfile
from subprocess import call

homedir = os.path.expanduser("~")
pythondir = homedir+"/python2.7.3"

pythonurl = "https://www.python.org/ftp/python/2.7.3/Python-2.7.3.tgz"
pipurl = "https://bootstrap.pypa.io/get-pip.py"

def download_file_with_prgressbar(url):
	file_name = url.split('/')[-1]
	u = urllib2.urlopen(url)

	f = open(file_name, 'wb')
	meta = u.info()
	file_size = int(meta.getheaders("Content-Length")[0])
	print "Downloading: %s Bytes: %s" % (file_name, file_size)

	file_size_dl = 0
	block_sz = 8192
	while True:
	    buffer = u.read(block_sz)
	    if not buffer:
	        break

	    file_size_dl += len(buffer)
	    f.write(buffer)
	    status = r"%10d  [%3.2f%%]" % (file_size_dl, file_size_dl * 100. / file_size)
	    status = status + chr(8)*(len(status)+1)
	    print status,

	f.close()

def unpress_file(filename):
	tar = tarfile.open(filename)
	tar.extractall()
	tar.close()

os.chdir(homedir)

call("sudo apt-get install -y git libbz2-dev libgdbm-dev liblzma-dev libreadline-dev \
libsqlite3-dev libssl-dev tcl-dev tk-dev dpkg-dev", shell=True)

if not os.path.isfile("Python-2.7.3.tgz"):
	download_file_with_prgressbar(pythonurl)

if not os.path.exists("Python-2.7.3"):
	unpress_file("Python-2.7.3.tgz")

# set up the path to python
os.chdir('Python-2.7.3')

call("./configure --prefix=%s/python2.7.3"%(homedir), shell=True)
call("sudo make&&sudo make install", shell=True)
call("sudo ln -sf %s/python2.7.3/bin/python2.7 /usr/bin/py"%(homedir), shell=True)

os.chdir(homedir)
if not os.path.isfile("get-pip.py"):
	download_file_with_prgressbar(pipurl)

call("py get-pip.py", shell=True)
call("sudo ln -sf %s/python2.7.3/bin/pip /usr/bin/pypip"%(homedir), shell=True)

call("pypip install virtualenv", shell=True)
call("pypip install django==1.5.8", shell=True)