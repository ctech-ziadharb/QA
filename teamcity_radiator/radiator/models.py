import base64
import json
import socket
import urllib.request
import time

from django.db import models


# Create your models here.
class TeamCity(object):
    """
        The real class for get information in jenkins server
    """
    
    def __init__(self, teamCityUrl=None):
        
        self.teamCityUrl = teamCityUrl
        
      
      
    def getJenkinsUrl(self):
        return self.teamCityUrl

    def setJenkinsUrl(self, teamCityUrl):
        self.teamCityUrl = teamCityUrl
           

#     def urlopen(self, url):
#         """
#             Binding to urllib2.urlopen
#         """
#   
#         timeout = self.timeout
#         request = urllib.request.Request(url)
#           
# #         if self.username:
# #             base64string = base64.encodestring('%s:%s' % (self.username, self.password)).replace('\n', '')
# #             request.add_header("Authorization", "Basic %s" % base64string)   
#   
#         return urllib.request.urlopen(request, timeout=timeout)

    def get_all_builds(self, teamCityProjectName):
        """
            List all jobs of a view for the jenkins server
        """
       
        datas = urlopen('%s/app/rest/buildTypes/id:%s/builds' % \
                (self.teamCityUrl, teamCityProjectName))
         
        if not datas:
            raise ValueError(
                'Error getting build data from Jenkins server at %s' % \
                    self.url)

        ret = []
        html = datas.read()        
        jobs = json.loads(html.decode())
        for job in jobs['builds']:
            buildData = urlopen('%s/job/%s/%s/api/json' %(self.teamCityUrl, teamCityProjectName, job['number']))
            jsonResult = json.loads(buildData.read().decode())
            buildResult = jsonResult['result']
            changeSet = jsonResult['changeSet']
            changeSetItems = changeSet['items']
            
            if len(changeSetItems) != 0:
                 changeAuthor =  changeSetItems[0]['author']['fullName']
                 commitId = changeSetItems[0]['commitId']
                 newjob = {'author': changeAuthor, 'commitId': commitId, 'result': buildResult }
                 ret.append(newjob)
            else:
                changeAuthor =  'none'
                commitId = 'none'
                newjob = {'author': changeAuthor, 'commitId': commitId, 'result': buildResult }
                ret.append(newjob)
            
        return ret
    
    def getBuildResult(self, jobName, buildNumber):
        
        lastBuildColorDic = {'color' : 'none'}
        
        latestBuild = False
        
        while (latestBuild == False):
            buildList = urlopen('%s/job/%s/api/json?tree=builds[number]' % (self.teamCityUrl, jobName))
            buildListDic = json.loads((buildList.read()).decode())
            if ((buildListDic['builds'])[0]['number'] == buildNumber):
                latestBuild = True
        
        while ("anime" in lastBuildColorDic['color'] or lastBuildColorDic['color'] == 'none') :
            lastBuildColorInfo = urlopen('%s/job/%s/api/json?tree=color' % (self.teamCityUrl, jobName))
            dic = json.loads((lastBuildColorInfo.read()).decode())
            lastBuildColorDic['color'] = dic['color']
            
        time.sleep(2)
            
        if (lastBuildColorDic['color'] == 'blue'):
            return 'SUCCESS'
        elif (lastBuildColorDic['color'] == 'red'):
            return 'FAILURE'           
   
    def getNextBuildNumber(self, jobName):
        nextBuild = urlopen('%s/job/%s/api/json?tree=nextBuildNumber' % (self.teamCityUrl, jobName))
        nextBuildDic = json.loads((nextBuild.read()).decode())
        return nextBuildDic['nextBuildNumber']
    
    def getRegressionTestBuilds(self):
        pass
    
    @staticmethod
    def translate_color_to_status(color):
        """
            Translate color of jenkins to status for view
        """

        colors = {'blue': ['successful'], \
                'blue_anime': ['successful', 'building'], \
                'red': ['failed'], \
                'red_anime': ['failed', 'building'], \
                'yellow': ['unstable'], \
                'yellow_anime': ['unstable', 'building'], \
                'aborted': ['cancelled'], \
                'aborted_anime': ['cancelled', 'building'], \
                'disabled': ['disabled'], \
                'default': ['unknown']}

        return colors.get(color, colors.get('default'))

    def get_blame_for(self, jobname):
        """
            If you have to blame someone for bad work :)
        """

        datas = self.urlopen(
            "%s/job/%s/lastBuild/api/json?tree=culprits[fullName]" \
                % (self.url, jobname))

        culprits = json.loads(datas.read())

        if not culprits.get('culprits'):
            return "Unknown"

        return culprits['culprits'][0]['fullName']
    
    
def urlopen(url):
        
    request = urllib.request.Request(url, headers={'Content-Type': 'application/xml'}) 
    return urllib.request.urlopen(request)
    

