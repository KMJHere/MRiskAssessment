// 페이지 공통 초기화
var commonDevice = null;
var commonContextPath = ""; 

$(document).ready(function() {	 
	for (var i = 0, t = document.getElementsByTagName("script"); i < t.length; i++) {
		var sSrc = t[i].getAttribute("src");
		if (sSrc != null && sSrc.indexOf("/js/common.js") >= 0 && sSrc.indexOf("?") >= 0) {
			if (sSrc.split("?")[1] != null && sSrc.split("?")[1] != "") {
				for (var j = 0, u = sSrc.split("?")[1].split("&"); j < u.length; j++) {
					if (u[j].indexOf("=") >= 0) {
						if (u[j].split("=")[0] == "contextPath") {
							commonContextPath = u[j].split("=")[1];
						}
					}	
				}
			}			
		}	
	} 	
});	

// 입력값이 NULL인지 체크
function commonIsNull(asValue) {
    if (asValue == null || asValue == undefined || asValue.toString().replace(/ /g,"") == "") {
        return true;
    }
    return false;
}

// 확장자 이미지 가져오기  
function commonGetImgByExt(psExt) {
	var lsExt = psExt.toUpperCase();
	
	switch (lsExt) {
		case "PPTX" :
			lsExt = "PPT";
			break;
		case "XLSX" :
			lsExt = "XLS";
			break;
		case "DOCX" :
			lsExt = "DOC";
			break;				
	}
	
	switch (lsExt) {
		case "BMP" :	
		case "EXE" :
		case "GIF" :
		case "HWP" :
		case "JPG" :
		case "PDF" :
		case "PPT" :
		case "RAR" :
		case "ZIP" :
		case "TXT" :																	
		case "XLS" :				
			return "/img/FileIcon/" + lsExt.toLowerCase() + ".gif";
			break;
		default :
			return "/img/FileIcon/unknown.gif";
	}			 
} 

// 문자 Encoding
function encodingToChar(param) { 
    var encode = '';

    for (var i = 0; i < param.length; i++) {
        var len  = '' + param.charCodeAt(i);
        var token = '' + len.length;
        encode  += token + param.charCodeAt(i);
    }

    return encode;
}

// 문자 Decoding
function decodingToChar(param) { 
    var sb = '';
    var pos = 0;
    var flg = true;

    if (param != null) {
        if (param.length > 1) {
            while (flg) {
                var sLen = param.substring(pos, ++pos);
                var nLen = 0;

                try {
                	nLen = parseInt(sLen);
                } catch(e) {
                    nLen = 0;
                }

                var code = '';
                if ((pos+nLen) > param.length) code = param.substring(pos);
                else code = param.substring(pos, (pos+nLen));

                pos += nLen;
                sb += String.fromCharCode(code);

                if (pos >= param.length) flg = false;
            }
        }
    }
    
    return sb;
}

function commonMovePage(psPage, psQueryString) {	
	var sPage = commonContextPath;
	
	if (psPage == "main") { 
		sPage += "/";  // 메인 
	} else if (psPage == "greeting") { 
		sPage += "/sfas/";  // 위험성평가
	}
	
	var sQueryString = ""; 
	if (!commonIsNull(psQueryString)) {
		var sJoinString = "?";
		if (sPage.indexOf("?") > -1) sJoinString = "&";
		
		sQueryString += (sJoinString + psQueryString);
	}
	
	location.href = sPage + sQueryString; 
}


function commonMovePageSearch(psCond) {
	if (!commonIsNull(psCond)) {
		location.href = commonContextPath + "/wp/common/searchResult?cond=" + psCond;
	}
}

async function uploadToServer(formObj) {
	console.log("upload..");
	
	const response = await axios({
		method: 'post',
		url: '/upload',
		data: formObj,
		headers: {
			'Content-Type': 'multipart/form-data',			
		},	
	});
	
	return response.data;
}

async function removeFileToServer(jFileGrpNo, fileName) {
	const response = await axios.delete('/reomve/${jFileGrpNo}_${fileName}');
	
	return response.data;
}