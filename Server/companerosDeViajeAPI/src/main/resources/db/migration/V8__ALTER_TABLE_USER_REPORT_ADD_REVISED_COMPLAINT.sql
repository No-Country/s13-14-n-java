alter table user_reports add revised_complaint tinyint;
update user_reports set revised_complaint = 1;