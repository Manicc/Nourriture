from django.shortcuts import render
from manage.models import Api, ApiGroup


def show_urls(urllist, depth=0):
    for entry in urllist:
        if entry.regex.pattern == '^admin/':
            continue
        print "  " * depth, entry.regex.pattern
        if hasattr(entry, 'url_patterns'):
            show_urls(entry.url_patterns, depth + 1)


def index(request):
    return render(request, 'index.html')


def api(request):
    group = ApiGroup.objects.all().order_by('id')
    api_group = list()
    for item in group:
        api_group.append((item.name, Api.objects.filter(group=item).order_by('id')))

    context = {'api': api_group}
    return render(request, 'api.html', context)