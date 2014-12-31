from datetime import datetime
from django.contrib.auth.models import User
from django.test import TestCase, Client

# Create your tests here.
from oauth2_provider.models import Application, AccessToken


def setup_user_token():
    # create user
    user = User.objects.create_user('hongzhi', password='1')

    # create oauth application
    app = Application()
    app.user = user
    app.save()

    token = AccessToken()
    token.user = user
    token.expires = datetime.now().replace(year=2015)
    token.application = app
    token.token = '987654321'
    token.save()

    return token.token


class CommentTest(TestCase):
    def setUp(self):
        self.extra = {
            'HTTP_AUTHORIZATION': 'Bearer ' + setup_user_token(),
        }

        Client().post('/ingredient/1/comment/', {'content': 'this is a test comment!'}, **self.extra)


    def test_post_comment(self):
        c = Client()
        response = c.post('/ingredient/1/comment/', {'content': 'this is a test comment!'}, **self.extra)
        self.assertEqual(response.status_code, 201)

    def test_get_comment(self):
        c = Client()
        c.post('/ingredient/1/comment/', {'content': 'this is a test comment!'}, **self.extra)
        response = c.get('/ingredient/1/comment/')
        self.assertEqual(response.status_code, 200)
        self.assertEqual(len(response.content)>0, True)


class FavoriteTest(TestCase):
    def setUp(self):
        self.extra = {
            'HTTP_AUTHORIZATION': 'Bearer ' + setup_user_token(),
        }

    def post_favorite(self):
        c = Client()
        c.post('/ingredient/1/favorite/', **self.extra)

    def post_favorite_no_id(self):
        c = Client()
        c.post('/ingredient/219749238/favorite/', **self.extra)