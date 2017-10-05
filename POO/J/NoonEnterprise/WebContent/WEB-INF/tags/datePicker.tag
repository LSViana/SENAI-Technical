<%@ attribute name="id" required="true" %>
<input id="${ id }" name="${ id }" />
<script>
	$("#${ id }").datepicker({ dateFormat: 'dd/mm/yy' });
</script>