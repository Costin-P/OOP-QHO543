<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
    $(function () {
        $("#zoneid").on("change", function () {
            //alert(this.value);
            $.post("${pageContext.request.contextPath}/getStationByZone",
                    {
                        zoneid: this.value
                    },
                    function (data) {
                        //alert(data);
                        $("#stationname").html(data);
                    });
        });
    });
</script>
<c:choose>
    <c:when test="${action.equals('add')}">
        <h2>Add Gate</h2>

        <form method="POST" action="insGate">
            <table>
                <tr>
                    <th>Zone Id</th>
                    <td>
                        <select name="zoneid" id="zoneid">
                            <option>Select ZoneId</option>
                            <c:forEach var="zone" items="${zones}">
                                <option value="${zone.id}">
                                    ${zone.id}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Station Name</th>
                    <td>
                        <select name="stationname" id="stationname">
                            <option>select station Name</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Gate No</th>
                    <td>
                        <input type="text" name="gateno" value="" />
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="submit" /></td>
                    <td><input type="reset" value="reset" /></td>
                </tr>
            </table>
        </form>
    </c:when>
    <c:when test="${action.equals('edit')}">
        <h2>Edit Gate</h2>
        <form method="POST" action="updGate">
            <input type="hidden" name="id" value="${gate.id}" />
            <table>
                <tr>
                    <th>Zone Id</th>
                    <td>
                        <input type="text" name="zoneid" value="${gate.zoneid}" readonly="readonly" />
                    </td>
                </tr>
                <tr>
                    <th>Station Name</th>
                    <td>
                        <input type="text" name="stationname" value="${gate.stationname}" readonly="readonly" />
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Gate No</th>
                    <td>
                        <input type="text" name="gateno" value="${gate.gateno}" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="submit" />
                    </td>
                    <td><input type="reset" value="reset" /></td>
                </tr>
            </table>
        </form>
    </c:when>
</c:choose>