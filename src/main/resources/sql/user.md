select
===
select * from sys_user where 1=1
@if(!isEmpty(userName)){
and username = #userName#
@}