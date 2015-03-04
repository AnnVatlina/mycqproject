<%@include file="/apps/mycqproject/common/global.jsp" %>
<%@page session="false" contentType="text/html; charset=utf-8" %>

<form action="/bin/result.json" id="search_form">
    <div class="input-control text size6 margin20 nrm">
        <input type="text" name="q" id="search_query" placeholder="${properties.placeholderSearch}" value="${fn:escapeXml(search.query)}">
        <button class="btn-search"></button>
    </div>
</form>
<script type="text/javascript">
    $("#search_form").submit(function(event) {
        $.ajax(
                "/bin/result.json",
                {
                    data: {
                        q: $("#search_query").val()
                    },
                    success: function(data) {
                        sessionStorage.setItem("searchResult", data);
                        window.location = "/content/mycqproject/en/searchresult.html";
                    },
                    error: function(xhr, status) {
                        $("#error_message").html(xhr.responseText).show();
                    }
                }
        );
        event.preventDefault();
        return false;
    });
</script>