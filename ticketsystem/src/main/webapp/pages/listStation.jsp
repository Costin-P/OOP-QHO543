<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Stations</h2>
<table border="1" width="80%">
    <thead>
        <tr>
            <th>#</th>
            <th>Zone Id</th>
            <th>Station Name</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="station" items="${stations}"  varStatus="loopCounter">
            <tr>
                <td>${loopCounter.count}</td>
                <td>${station.zoneid}</td>
                <td>${station.stationname}</td>
                <td>
                    <form method="post" action="editStation">
                        <input type="hidden" name="zoneid" value="${station.zoneid}" />
                        <input type="hidden" name="stationname" value="${station.stationname}" />
                        <input type="submit" value="edit" name="edit" />
                    </form>
                </td>
                <td>
                    <form method="post" action="delStation">
                        <input type="hidden" name="zoneid" value="${station.zoneid}" />
                        <input type="hidden" name="stationname" value="${station.stationname}" />
                        <input type="submit" value="delete" name="delete" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>