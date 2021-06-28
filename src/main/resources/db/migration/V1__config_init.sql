/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Michael Rendón Villa
 * Created: 28/06/2021
 */

CREATE TABLE public."roles"
(
    "role_id" serial NOT NULL,
    "role_name" character varying(200) NOT NULL,
    CONSTRAINT "role_pkey" PRIMARY KEY ("role_id")
);


INSERT INTO public."roles"("role_id", "role_name") VALUES (1,'Asesor');
INSERT INTO public."roles"("role_id", "role_name") VALUES (2,'Soporte');

ALTER SEQUENCE roles_role_id_seq RESTART WITH 3;


