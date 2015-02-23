<%@include file="/apps/mycqproject/common/global.jsp" %>
<header class="margin20 nrm nlm">
    <div class="clearfix">
        <div class="place-right">
            <form>
                <div class="input-control text size6 margin20 nrm">
                    <input type="text" name="q" placeholder="Search...">
                    <button class="btn-search"></button>
                </div>
            </form>
        </div>
        <a class="place-left" href="/content/mycqproject/en.html" title="">
            <h1>News Portal</h1>
        </a>
    </div>

    <div class="main-menu-wrapper">
        <cq:include path="navTop" resourceType="mycqproject/components/listpages"/>
    </div>
</header>