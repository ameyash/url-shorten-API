PGDMP                        }            url-shortener    17.1    17.1     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            �           1262    24628    url-shortener    DATABASE     �   CREATE DATABASE "url-shortener" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_India.1252';
    DROP DATABASE "url-shortener";
                     postgres    false            �            1259    24630    url    TABLE     �   CREATE TABLE public.url (
    id bigint NOT NULL,
    click_count bigint,
    created_at timestamp(6) without time zone,
    original_url character varying(255) NOT NULL,
    short_url character varying(255) NOT NULL,
    user_id bigint
);
    DROP TABLE public.url;
       public         heap r       postgres    false            �            1259    24629 
   url_id_seq    SEQUENCE     �   ALTER TABLE public.url ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.url_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    218            �            1259    24640    users    TABLE     �   CREATE TABLE public.users (
    id bigint NOT NULL,
    email character varying(255),
    password character varying(255),
    username character varying(255)
);
    DROP TABLE public.users;
       public         heap r       postgres    false            �            1259    24639    users_id_seq    SEQUENCE     �   ALTER TABLE public.users ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    220            �          0    24630    url 
   TABLE DATA           \   COPY public.url (id, click_count, created_at, original_url, short_url, user_id) FROM stdin;
    public               postgres    false    218   N       �          0    24640    users 
   TABLE DATA           >   COPY public.users (id, email, password, username) FROM stdin;
    public               postgres    false    220   �       �           0    0 
   url_id_seq    SEQUENCE SET     8   SELECT pg_catalog.setval('public.url_id_seq', 8, true);
          public               postgres    false    217            �           0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 3, true);
          public               postgres    false    219            ]           2606    24638    url ukqj1hl3a9n83kqdax9ugtqacsd 
   CONSTRAINT     _   ALTER TABLE ONLY public.url
    ADD CONSTRAINT ukqj1hl3a9n83kqdax9ugtqacsd UNIQUE (short_url);
 I   ALTER TABLE ONLY public.url DROP CONSTRAINT ukqj1hl3a9n83kqdax9ugtqacsd;
       public                 postgres    false    218            _           2606    24636    url url_pkey 
   CONSTRAINT     J   ALTER TABLE ONLY public.url
    ADD CONSTRAINT url_pkey PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.url DROP CONSTRAINT url_pkey;
       public                 postgres    false    218            a           2606    24646    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public                 postgres    false    220            b           2606    24647    url fkt9oxwarfpcswxu1l63hxawfsq    FK CONSTRAINT     ~   ALTER TABLE ONLY public.url
    ADD CONSTRAINT fkt9oxwarfpcswxu1l63hxawfsq FOREIGN KEY (user_id) REFERENCES public.users(id);
 I   ALTER TABLE ONLY public.url DROP CONSTRAINT fkt9oxwarfpcswxu1l63hxawfsq;
       public               postgres    false    218    4705    220            �   =  x�u�Kk�@Fד_�ʝ�{�=Rl�(��bn�$>�����DE�"���{�A�=d=�N�CM�P���rpa��|��f4)r�<_��/�|p"�q��Z)��뚮�bu%���,�%%���
\K4��u�
�Ĭ)k�����P��P�NqoIjy���З��ؼ���H���/O�����6�ո�QO+ X*l�`^$�|� �5���A�P9���Z=~���׌Q4�S'��Rq[���r�	��r��/��۱ş\�U�GEw�9����H���ʧ?���f�f�>o����l��A�=�A�����      �   �   x�m�ˎ�0 ��5}�-�t�DT��\�7o�r�^3�lff{��;�t�H2f����կf���mT5HC�aX
���;�n�h�Y��:YO�!?��|� K,���S:��,�`\�?���R�R�;9�PoUk6j���c[�� �T��-��fk!Ѭw�����}�����4�XP�d�+�ik�`ׅ�~�>�8�]˨8��cK_w��� �OET�     