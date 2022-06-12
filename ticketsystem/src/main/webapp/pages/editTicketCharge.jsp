<h2>Edit Ticket Charge</h2>
<form method="POST" action="updTicketCharge">
    <input type="hidden" name="id" value="${ticketCharge.id}" />
    <table>
        <tr>
            <th>pick_charge</th>
            <td><input type="text" name="pickcharge" value="${ticketCharge.pickcharge}"/></td>
        </tr>
        <tr>
            <th>offpick_charge</th>
            <td><input type="text" name="offpickcharge" value="${ticketCharge.offpickcharge}" /></td>
        </tr>
        <tr>
            <th>pick_fromtime (HH:mm:ss)</th>
            <td><input type="text" name="pickfromtime" value="${ticketCharge.pickfromtime}"/></td>
        </tr>
        <tr>
            <th>pick_totime (HH:mm:ss)</th>
            <td><input type="text" name="picktotime" value="${ticketCharge.picktotime}" /></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="submit" />
            </td>
            <td><input type="reset" value="reset" /></td>
        </tr>
    </table>
</form>