<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Ticket Charges</h2>
<table border="1" width="80%">
    <thead>
        <tr>
            <th>#</th>
            <th>pick Charge</th>
            <th>offpick Charge</th>
            <th>pick Fromtime</th>
            <th>pick Totime</th>
            <th>Edit</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="ticketCharge" items="${ticketCharges}"  varStatus="loopCounter">
            <tr>
                <td>${loopCounter.count}</td>
                <td>${ticketCharge.pickcharge}</td>
                <td>${ticketCharge.offpickcharge}</td>
                <td>${ticketCharge.pickfromtime}</td>
                <td>${ticketCharge.picktotime}</td>
                <td>
                    <form method="post" action="editTicketCharge">
                        <input type="hidden" name="id" value="${ticketCharge.id}" />
                        <input type="submit" value="edit" name="edit" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>