from server import wsgi
import sae
import os
import sys
import sae
from sae.ext.shell import ShellMiddleware

root = os.path.dirname(__file__)

sys.path.insert(0, os.path.join(root, 'site-packages'))
application = sae.create_wsgi_app(ShellMiddleware(wsgi.application))
