<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
		#l-map{height:100%;width:78%;float:left;border-right:2px solid #bcbcbc;}
		#r-result{height:100%;width:20%;float:left;}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=TGfKNmGBxMeQrysRtjEvplqsupaHPbSv"></script>
	<title>添加多个标注点</title>
</head>
<body>
	<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(117.160, 38.892);
	map.centerAndZoom(point, 18);
	// 编写自定义函数,创建标注
	function addMarker(point){
	  var marker = new BMap.Marker(point);
	  map.addOverlay(marker);
	}
	// 随机向地图添加25个标注
	var bounds = map.getBounds();
	var sw = bounds.getSouthWest();
	var ne = bounds.getNorthEast();
	var lngSpan = Math.abs(sw.lng - ne.lng);
	var latSpan = Math.abs(ne.lat - sw.lat);
	/* for (var i = 0; i < 5; i ++) { */
		 //后者越大越向下  前者越大越向右
		var point1 = new BMap.Point(sw.lng + lngSpan * (0.45), ne.lat - latSpan * (0.6));
		var point2 = new BMap.Point(sw.lng + lngSpan * (0.9), ne.lat - latSpan * (0.75));
		var point3 = new BMap.Point(sw.lng + lngSpan * (0.5), ne.lat - latSpan * (0.45));
		var point4 = new BMap.Point(sw.lng + lngSpan * (0.9), ne.lat - latSpan * (0.83));
		addMarker(point1);
		  addMarker(point2)
		  addMarker(point3)
		  addMarker(point4)
	//}
</script>
