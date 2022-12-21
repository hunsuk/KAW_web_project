
--제품
insert into pallet_item(category,front_type,size,weight,material,uniqueness,img_uri,standard) value("표준형","11A형","1,100mm X 1,100mm X 150mm","19.5kg","HDPE","적재하중 1Ton/4방 차입형 편면 사용형","http://ajnetworks.co.kr/assets/uploads/product/product_11A_1.png","A11");
insert into pallet_item(category,front_type,size,weight,material,uniqueness,img_uri,standard) value("표준형","12A형","1,200mm X 1,000mm X 150mm","19.5kg","HDPE","적재하중 1Ton/4방 차입형 편면 사용형","http://ajnetworks.co.kr/assets/uploads/product/product_12A_1.png","A12");
insert into pallet_item(category,front_type,size,weight,material,uniqueness,img_uri,standard) value("표준형","11B형","1,100mm X 1,100mm X 150mm","26.2kg","HDPE","적재하중 1Ton/4방 차입형 편면 사용형","http://ajnetworks.co.kr/assets/uploads/product/product_11B_1.png","B11");
insert into pallet_item(category,front_type,size,weight,material,uniqueness,img_uri,standard) value("표준형","13B형","1,300mm X 1,100mm X 150mm","25.0kg","HDPE","적재하중 1Ton/4방 차입형 양면 사용형","http://ajnetworks.co.kr/assets/uploads/product/product_13B_1.png","B13");
insert into pallet_item(category,front_type,size,weight,material,uniqueness,img_uri,standard) value("표준형","15A형","1,460mm X 1,130mm X 150mm","27.5kg","HDPE","적재하중 1Ton/4방 차입형 편면 사용형","http://ajnetworks.co.kr/assets/uploads/product/product_15A_1.png","A15");
insert into pallet_item(category,front_type,size,weight,material,uniqueness,img_uri,standard) value("제지용","국판형","650mm X 950mm X 145mm","13.0kg","HDPE","적재하중 1Ton/4방 차입형 양면 사용형","http://ajnetworks.co.kr/assets/uploads/product/product_gookpan_1.png","국판");
insert into pallet_item(category,front_type,size,weight,material,uniqueness,img_uri,standard) value("제지용","4*6판형","800mm X 1,100mm X 145mm","20.0kg","HDPE","적재하중 1Ton/4방 차입형 양면 사용형","http://ajnetworks.co.kr/assets/uploads/product/product_4x6_1.png","4x6판");
insert into pallet_item(category,front_type,size,weight,material,uniqueness,img_uri,standard) value("제지용","국배판형","950mm X 1,280mm X 140mm","26.0kg","HDPE","적재하중 1Ton/4방 차입형 양면 사용형","http://ajnetworks.co.kr/assets/uploads/product/product_gookbaepan_1.png","국배판");

--고정 유저
insert into user(auth,corp_name,create_at,email,location,manager_name,modified_at,password,phone)value("ROLE_USER","삼성 물산","2022-12-17 09:21:59","daw916@naver.com","11-180","김현석","2022-12-17 09:21:59","$2a$10$v/NhV.Nsme1rsH9vXkIG6.yT7UXmmE9YwGidUlqbnQlh7KGEwhk4a","01021033096");

insert into user(auth,corp_name,create_at,email,location,manager_name,modified_at,password,phone)value("ROLE_ADMIN,ROLE_USER","삼성 물산","2022-12-17 09:21:59","daw564@naver.com","11-180","김아무게","2022-12-17 09:21:59","$2a$10$EEaPmZ93gldYwpR.nonIB.oksAjG5/ltwEHAFIvQLP2dLqWs2aSgW","01022222222");
