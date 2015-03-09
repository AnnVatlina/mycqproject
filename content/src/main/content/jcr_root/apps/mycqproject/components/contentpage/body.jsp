<%@include file="/apps/mycqproject/common/global.jsp" %>
<body>
    <div class="metro">
        <div class="container">
            <cq:include script="headerBody.jsp"/>
            <div class="main-content clearfix">
                <div class="tile-area no-padding clearfix">
                    <div class="tile-group no-margin no-padding clearfix" style="width: 100%">
                        <div class="tile double quadro-vertical bg-gray ol-transparent" style="float: right; ">
                            <div class="tile-content">
                                <div class="brand">
                                    <span class="label fg-white">ads here...</span>
                                </div>
                            </div>
                        </div>

                        <div class="tile quadro double-vertical ol-transparent">
                            <div class="tile-content">
                                <cq:include path="topContent" resourceType="foundation/components/parsys"/>
                            </div>
                        </div>
                        <div class="tile bg-lightBlue ol-transparent">
                            <div class="tile-content icon">
                                <span class="icon-windows"></span>
                            </div>
                        </div>
                        <div class="tile bg-orange ol-transparent">
                            <div class="tile-content icon">
                                <span class="icon-music"></span>
                            </div>
                        </div>
                        <div class="tile ol-transparent bg-teal">
                            <div class="tile-content icon">
                                <span class="icon-facebook"></span>
                            </div>
                        </div>
                        <div class="tile ol-transparent bg-green">
                            <div class="tile-content icon">
                                <span class="icon-twitter"></span>
                            </div>
                        </div>

                        <div class="tile triple double-vertical ol-transparent bg-white">
                            <cq:include script="leftpanel.jsp"/>
                        </div>
                        <div class="tile triple double-vertical ol-transparent bg-white">
                            <cq:include script="rightpanel.jsp"/>
                        </div>
                    </div>
                    <!-- End first group -->
                </div>
            </div>
            <!-- End of tiles -->

            <cq:include script="footer.jsp"/>
        </div>
    </div>
</body>