from django.shortcuts import render
from server import urls


def show_urls(urllist, depth=0):
    for entry in urllist:
        if entry.regex.pattern == '^admin/':
            continue
        print "  " * depth, entry.regex.pattern
        if hasattr(entry, 'url_patterns'):
            show_urls(entry.url_patterns, depth + 1)


def index(request):
    show_urls(urls.urlpatterns)
    return render(request, 'index.html');