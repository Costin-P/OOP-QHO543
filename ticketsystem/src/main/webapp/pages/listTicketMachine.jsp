<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Ticket Machines</h2>
<table border="1" width="80%">
    <thead>
        <tr>
            <th>#</th>
            <th>Machine Id</th>
            <th>Zone Id</th>
            <th>Station Name</th>
            <th>Gate No</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="ticketMachine" items="${ticketMachines}"  varStatus="loopCounter">
            <tr>
                <td>${loopCounter.count}</td>
                <td>${ticketMachine.machineid}</td>
                <td>${ticketMachine.zoneid}</td>
                 <td>${ticketMachine.stationname}</td>
                <td>${ticketMachine.gateno}</td>
                <td>
                    <form method="post" action="editTicketMachine">
                        <input type="hidden" name="machineid" value="${ticketMachine.machineid}" />
                        <input type="submit" value="edit" name="edit" />
                    </form>
                </td>
                <td>
                    <form method="post" action="delTicketMachine">
                        <input type="hidden" name="machineid" value="${ticketMachine.machineid}" />
                        <input type="submit" value="delete" name="delete" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>