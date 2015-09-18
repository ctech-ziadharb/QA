from django.conf.urls import patterns, url
from radiator import views
from django.views.generic import TemplateView

urlpatterns = patterns('',
        url(r'^home/$', TemplateView.as_view(template_name='index.html')),
        url(r'^selenium/$', views.getTeamSeCityBuilds),
        url(r'^nextBuildNumber/$', views.getJenkinsNextBuildNumber),
#         url(r'^smoke/$', views.getJenkinsSmokeTestLastResult, name='smoke'),
        url(r'^regression/$', views.getJenkinsRegressionTestBuilds, name='regression'),

        )