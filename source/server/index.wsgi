from server import wsgi
import sae
import os
import sys
import sae
from sae.ext.shell import ShellMiddleware

application = sae.create_wsgi_app(ShellMiddleware(wsgi.application))
