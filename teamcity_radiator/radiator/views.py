from django.conf import settings
from django.http import HttpResponse
from django.template import RequestContext, loader
from django.shortcuts import render_to_response

from radiator.models import TeamCity
import json, os

    
teamCityUrl = getattr(settings, 'TEAMCITY_URL')
    
teamCity = TeamCity(teamCityUrl=teamCityUrl)

x=0

# Create your views here.
def getTeamSeCityBuilds(request):   
    
    
    teamCityProjectName = getattr(settings, 'TEAMCITY_DASHBOARD_WEB_PROJECT_NAME') 
    
    jobs = None
    result = None

    try:
        jobs = teamCity.get_all_builds(teamCityProjectName)
    except Exception as error:
        result = {'status': 'error', 'content': str(error)}

    jobsFirstElement = True
    if not result and jobs:
        html = ''
        for job in jobs:             
            html += """<div class='boxed {2}'> 
                           
                                <table class = 'build_information'>
                                  <tr>
                                    <td>Author:</td>
                                    <td>{0}</td>  
                                  </tr>
                                  <tr>
                                    <td>Commit id:</td>
                                    <td>{1}</td>  
                                  </tr>
                                  <tr>
                                    <td>Build status:</td>
                                    <td>{2}</td>  
                                  </tr>
                               </table>
                    """.format(job['author'], job['commitId'], job['result'])
                       
            if (jobsFirstElement):
                html += """
                       <input type="Button" id="regression_btn"; onClick="startJenkinsRegressionJob(); this.value='Running regression tests..';this.disabled=true;" value="R">
                       <input type="Button" id="smoke_btn"; onClick="startJenkinsSmokeJob()" value="S">
                       """
                jobsFirstElement = False   
                
            html += """</div>
                       <br><br>"""
                    
        result = {'status': 'ok', 'content': html}     

    json_data = json.dumps( result)
#     json data is just a JSON string now. 
    return HttpResponse(json_data)    
#     return render_to_response('radiator/index.html',result, context_instance=RequestContext(request))



def getJenkinsSmokeTestLastResult(request):
    
    global  x
    x += 1
    print ("SMooooooooke test " + str(x))
    
    jenkinsSmokeJobName = getattr(settings, 'JENKINS_SMOKE_JOB_NAME') 
    jenkinsSmokeJobNextBuildNumber = teamCity.getNextBuildNumber(jenkinsSmokeJobName)
      
        
    json_data = json.dumps({"result":teamCity.getBuildResult(jenkinsSmokeJobName, jenkinsSmokeJobNextBuildNumber)})
#     json data is just a JSON string now. 
    return HttpResponse(json_data)    

def getJenkinsNextBuildNumber(request):
    jenkinsSmokeJobName = getattr(settings, 'JENKINS_SMOKE_JOB_NAME') 
    
    json_data = json.dumps({"nextBuildNumber":teamCity.getNextBuildNumber(request.GET['jobName'])})
#     json data is just a JSON string now. 
    return HttpResponse(json_data)    
    
def getJenkinsRegressionTestBuilds(request):
    TeamCity.getRegressionTestBuilds()
    pass



