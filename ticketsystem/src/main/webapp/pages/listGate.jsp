<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Gates</h2>
<table border="1" width="80%">
    <thead>
        <tr>
            <th>#</th>
            <th>Zone Id</th>
            <th>Station Name</th>
            <th>Gate No</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="gate" items="${gates}"  varStatus="loopCounter">
            <tr>
                <td>${loopCounter.count}</td>
                <td>${gate.zoneid}</td>
                <td>${gate.stationname}</td>
                <td>${gate.gateno}</td>
                <td>
                    <form method="post" action="editGate">
                        <input type="hidden" name="zoneid" value="${gate.zoneid}" />
                        <input type="hidden" name="stationname" value="${gate.stationname}" />
                        <input type="hidden" name="gateno" value="${gate.gateno}" />
                        <input type="submit" value="edit" name="edit" />
                    </form>
                </td>
                <td>
                    <form method="post" action="delGate">
                        <input type="hidden" name="zoneid" value="${gate.zoneid}" />
                        <input type="hidden" name="stationname" value="${gate.stationname}" />
                        <input type="hidden" name="gateno" value="${gate.gateno}" />
                        <input type="submit" value="delete" name="delete" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>