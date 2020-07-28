# Infrrd-Assignment-



http://localhost:8080/sanam/startDataprocessing   ======= End point to start DataProcessing(POST)
Request :
{
	"uuid" :12,
	"data" : "sanam"
}
Response : starts dataprocessing if not not started
gives exception if already started by other thread


http://localhost:8080/sanam/stopDataprocessing?UUID=12      ====End point to stop dataProcessing (GET)
response :     "data": "processing of request with 12stopped",

 gives exception if its already stopped by other thread like
 error": "processing of request with 12alreadyDisruptedByOtherThread",
    "data": "processing of request with 12alreadyDisruptedByOtherThread",
    "dataSet": null
    
    
    
    http://localhost:8080/sanam//getProceesedids     ==== End point to fetch all UUIDS fully processed(Started and stropepd)
   Response
   
    {
    "error": null,
    "data": null,
    "dataSet": [
        12
    ]
}


PLEASE USE POSTMAN IF SWAGGEER CAUSES PROBLEMS



