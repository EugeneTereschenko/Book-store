DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS cards;
DROP TABLE IF EXISTS carts;
DROP TABLE IF EXISTS deliveries;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS coupons;
DROP TABLE IF EXISTS active_admin_comments;
DROP TABLE IF EXISTS admin_users;

create table if not exists users (
  id integer not null auto_increment,
  email varchar(20),
  encrypted_password varchar(20),
  reset_password_token varchar(20),
  reset_password_sent_at datetime,
  remember_created_at datetime,
  sign_in_count int,
  current_sign_in_at datetime,
  last_sign_in_at datetime,
  current_sign_in_ip varchar(20),
  last_sign_in_ip varchar(20),
  created_at datetime,
  update_at datetime,
  role int,
  name varchar(20),
  provider varchar(20),
  uid varchar(20),
  image text,
  primary key (id)
);

create table if not exists admin_users (
  id integer not null auto_increment,
  email varchar(300),
  encrypted_password varchar(200),
  reset_password_token varchar(200),
  reset_password_sent_at datetime,
  remember_created_at datetime,
  created_at datetime,
  update_at datetime,
  primary key (id),
  unique (
    email
  )
);


create table if not exists books (
  id integer not null auto_increment,
  title varchar(300),
  description text,
  price integer,
  height integer,
  width integer,
  depth integer,
  in_stock integer,
  author varchar(300),
  image varchar(300),
  materials varchar(300),
  year varchar(300),
  created_at datetime,
  update_at datetime,
  primary key (id),
  unique (
    title
  )
);

create table if not exists deliveries (
  id integer not null auto_increment,
  name varchar(300),
  time varchar(300),
  price int,
  created_at datetime,
  update_at datetime,
  primary key (id),
  unique (
    name
  )
);

create table if not exists cards (
  card_number varchar(200),
  name varchar(200),
  image varchar(200),
  cvv integer,
  expiration_month_year datetime,
  user_id int,
  created_at datetime,
  update_at datetime,
  foreign key (user_id)
  references users (id) on delete cascade,
  unique (
    user_id
  )
);

create table if not exists carts (
  id integer not null auto_increment,
  order_total_price int,
  created_at datetime,
  update_at datetime,
  user_id int,
  item_total_price int,
  coupon int,
  checkout_step varchar(200),
  delivery_id int,
  primary key (id),
  foreign key (user_id)
  references users (id) on delete cascade,
  foreign key (delivery_id)
  references deliveries (id) on delete cascade,
  unique (
    user_id,
    delivery_id
  )
);

create table if not exists categories (
  id integer not null auto_increment,
  type_of varchar(200),
  created_at datetime,
  update_at datetime,
  primary key (id),
  unique (
    type_of
  )
);

create table if not exists coupons (
  id integer not null auto_increment,
  code varchar(200),
  percent int,
  value int,
  created_at datetime,
  update_at datetime,
  primary key (id),
  unique (
    code
  )
);


create table if not exists items (
  book_id int,
  cart_id int,
  quantity int,
  created_at datetime,
  update_at datetime,
  foreign key (book_id)
  references books (id) on delete cascade,
  foreign key (cart_id)
  references carts (id) on delete cascade,
  unique (
    book_id,
    cart_id
  )
);


create table if not exists orders (
  id integer not null auto_increment,
  invoice varchar(200),
  item_total_price int,
  order_total_price int,
  coupon int,
  user_id int,
  state varchar(200),
  delivery_id int,
  created_at datetime,
  update_at datetime,
  primary key (id),
  foreign key (user_id)
  references users (id) on delete cascade,
  foreign key (delivery_id)
  references deliveries (id) on delete cascade,
  unique (
    user_id,
    delivery_id
  )
);

create table if not exists order_items (
  book_id int,
  order_id int,
  quantity int,
  created_at datetime,
  update_at datetime,
  foreign key (book_id)
  references books (id) on delete cascade,
  foreign key (order_id)
  references orders (id) on delete cascade,
  unique (
    book_id,
    order_id
  )
);

create table if not exists reviews (
  score int,
  reviewer_name varchar(200),
  body text,
  user_id int,
  state varchar(200),
  book_id int,
  created_at datetime,
  update_at datetime,
  foreign key (user_id)
  references users (id) on delete cascade,
  foreign key (book_id)
  references books (id) on delete cascade,
  unique (
    user_id,
    book_id
  )
);


create table if not exists active_admin_comments (
  id integer not null auto_increment,
  namespace varchar(200),
  body TEXT,
  resource_type varchar(200),
  resourse_id int,
  author_type varchar(200),
  author_id int,
  created_at datetime,
  update_at datetime,
  primary key (id)
);

create table if not exists addresses (
  address_type varchar(300),
  first_name varchar(300),
  last_name varchar(300),
  address varchar(300),
  city varchar(300),
  zip int,
  country varchar(300),
  phone varchar(300),
  user_id int,
  checkbox_id int,
  order_id int,
  created_at datetime,
  update_at datetime,
  foreign key (user_id)
  references users (id) on delete cascade,
  foreign key (order_id)
  references orders (id) on delete cascade,
  unique (
    user_id,
    order_id
  )
);
