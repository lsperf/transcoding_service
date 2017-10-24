/**
 * Created by ls on 10/23/17.
 */


function checkTranscodingStatus(){


    $.ajax({
        type: "GET",
        url: "/transcoding.status",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        success: function(result,status,jqXHR ){
            //alert(result);
            result = JSON.stringify(result);
            //alert(result);
            $.each(JSON.parse(result), function(idx, obj) {

                if(idx=='Message'){

                    $('#transcoding_state').text("");
                    $('#transcoding_hls_url').empty();
                    $('#transcoding_duration').text("");
                    $('#transcoding_resolution').text("");
                    var message = JSON.parse(JSON.stringify(obj));
                    $.each(JSON.parse(message), function(messIdx, messObj) {

                        if(messIdx=='state') {
                            $('#transcoding_state').text('State: '+ messObj);
                        }else if(messIdx=='outputs'){
                            var outputs = JSON.stringify(messObj);

                            $.each(JSON.parse(outputs), function(outIdx, outObj) {

                                var width=0, height=0;
                                var outp = JSON.stringify(outObj);
                                $.each(JSON.parse(outp), function(ouIdx, ouObj) {
                                    if(ouIdx=='key') {
                                        var dlink = '<a href="https://s3-us-west-2.amazonaws.com/lgtranscoding-output/'+
                                                 ouObj+'" download>'+ouObj+'</a>';
                                        //$('#transcoding_hls_url').html('<a href="http://www.google.com">Google</a>');
                                        $('#transcoding_hls_url').html('Download: '+ dlink);
                                    }else if(ouIdx=='duration') {
                                        $('#transcoding_duration').text('Duration: '+ouObj+' min');
                                    }else if(ouIdx=='width') {
                                        width = ouObj;
                                    }else if(ouIdx=='height') {
                                        height = ouObj;
                                    }
                                });

                                if(width > 0){
                                    $('#transcoding_resolution').text('Resolution: '+width+'x'+height);
                                }

                            });

                        }


                    });
                }


            });

        },
        error : function(jqXHR, textStatus, errorThrown) {
            console.log('failed');
        }
    });

}

/// Transcoding completion check timer
setInterval(function() {
    checkTranscodingStatus();
}, 3000);
