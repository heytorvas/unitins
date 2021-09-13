from django.conf.urls import url
from psychologist.views import UserRegistrationView, UserLoginView

urlpatterns = [
    url('signup/', UserRegistrationView.as_view()),
    url('login/', UserLoginView.as_view()),
]