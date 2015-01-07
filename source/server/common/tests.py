from django.contrib.auth.models import User
from django.test import TestCase, Client

# Create your tests here.
from django.utils import timezone
from oauth2_provider.models import Application, AccessToken
from common.models import Favorite, TARGET_TYPE, Ingredient, IngredientCategory


def setup_user_token():
    # create user
    user = User.objects.create_user('hongzhi', password='1')

    # create oauth application
    app = Application()
    app.user = user
    app.save()

    token = AccessToken()
    token.user = user
    token.expires = timezone.now().replace(year=timezone.now().year + 1)
    token.application = app
    token.token = '987654321'
    token.save()

    return user, token.token


class CommentTest(TestCase):
    def setUp(self):
        self.user, self.token = setup_user_token()
        self.extra = {
            'HTTP_AUTHORIZATION': 'Bearer ' + self.token,
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
        self.assertEqual(len(response.content) > 0, True)


def create_favorite(user):
    # create category
    cat = IngredientCategory(name='test')
    cat.save()

    # create ingredient
    ingre = Ingredient(name='test', category=cat, image=None)
    ingre.save()

    fav = Favorite(user=user, target_type=TARGET_TYPE.get('ingredient'), target_id=1)
    fav.save()
    return fav


class FavoriteTest(TestCase):
    def setUp(self):
        self.user, self.token = setup_user_token()
        self.extra = {
            'HTTP_AUTHORIZATION': 'Bearer ' + self.token,
        }

    def test_post_favorite(self):
        c = Client()
        response = c.post('/ingredient/1/favorite/', **self.extra)

        self.assertEqual(response.status_code, 201)

    def test_delete_favorite(self):
        c = Client()
        # create a favorite
        c.post('/ingredient/1/favorite/', **self.extra)

        # delete this favorite
        response = c.delete('/ingredient/1/favorite/1/', **self.extra)

        self.assertEqual(response.status_code, 204)

    def test_get_user_favorite_list(self):
        # create a favorite
        create_favorite(self.user)

        c = Client()
        response = c.get('/user/1/favorite/', **self.extra)
        print response.content
        self.assertEqual(response.status_code, 200)




#
