<%@include file="/apps/mycqproject/common/global.jsp" %>
<%@page session="false" contentType="text/html; charset=utf-8" %>

<h2>Result:</h2>
<div id="result_items">

</div>

<script type="text/javascript">

    var data = sessionStorage.getItem("searchResult");
    var obj = JSON.parse(data);
    var items = "";
    if (obj.root.length != 0) {
        for (var i = 0; i < obj.root.length; i++) {
            items += '<p><a href="' + obj.root[i].path + '">';
            items += "#" + (i + 1) + ": " + obj.root[i].title;
            items += '</a></p>';
        }
    } else {
        items = "No Results"
    }
    $("#result_items").html(items);
    sessionStorage.removeItem("searchResult");

</script>