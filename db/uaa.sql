-- ----------------------------
--  数据库初始化脚本
-- ----------------------------
-- 1. 创建数据库
drop database if exists gemini_uaa;

create database gemini_uaa default character set utf8mb4 collate utf8mb4_general_ci;

use gemini_uaa;

set names utf8mb4;

-- 2. 创建表
-- ----------------------------
--  客户端信息表
-- ----------------------------
drop table if exists t_client_info;

create table t_client_info
(
    id                            bigint unsigned auto_increment                                         not null comment '主键id',
    client_id                     varchar(100)     default ''                                            not null comment '客户端id',
    client_secret                 varchar(100)     default ''                                            not null comment '客户端密钥',
    client_name                   varchar(100)     default ''                                            not null comment '客户端名称',
    client_authentication_methods varchar(1000)    default ''                                            not null comment '客户端认证方式',
    authorized_grant_types        varchar(1000)    default ''                                            not null comment '授权类型',
    redirect_uris                 varchar(1000)    default ''                                            not null comment '重定向地址',
    post_logout_redirect_uris     varchar(1000)    default ''                                            not null comment '登出重定向地址',
    scopes                        varchar(1000)    default ''                                            not null comment '作用域',
    client_settings               varchar(2000)    default ''                                            not null comment '客户端设置',
    token_settings                varchar(2000)    default ''                                            not null comment 'token设置',
    additional_information        varchar(2000)    default ''                                            not null comment '附加信息',
    client_id_issued_at           timestamp        default current_timestamp                             not null comment '客户端id发放时间',
    client_secret_expires_at      timestamp        default '9999-12-31 23:59:59'                         not null comment '客户端密钥过期时间',
    tenant_id                     bigint unsigned  default 0                                             not null comment '租户id 0:公共租户',
    version                       int unsigned     default 0                                             not null comment '版本号',
    deleted                       tinyint unsigned default 0                                             not null comment '是否删除 0:否 1:是',
    create_user_id                bigint unsigned  default 0                                             not null comment '创建人id',
    create_user_name              varchar(32)      default ''                                            not null comment '创建人姓名',
    create_time                   timestamp        default current_timestamp                             not null comment '创建时间',
    update_user_id                bigint unsigned  default 0                                             not null comment '更新人id',
    update_user_name              varchar(32)      default ''                                            not null comment '更新人姓名',
    update_time                   timestamp        default current_timestamp on update current_timestamp not null comment '更新时间',
    primary key (id),
    unique key uk_client_id (client_id),
    unique key uk_client_name (client_name)
) engine = InnoDB
  default charset = utf8mb4 comment '客户端信息表';

-- ----------------------------
--  授权信息表
-- ----------------------------
drop table if exists t_authorization_info;

create table t_authorization_info
(
    id               bigint unsigned auto_increment                                         not null comment '主键id',
    client_id        bigint unsigned  default 0                                             not null comment '客户端id',
    user_id          bigint unsigned  default 0                                             not null comment '用户id',
    grant_type       varchar(100)     default ''                                            not null comment '授权类型',
    scopes           varchar(1000)    default ''                                            not null comment '作用域',
    attributes       varchar(2000)    default ''                                            not null comment '属性',
    consent          tinyint unsigned default 0                                             not null comment '是否同意 0:否 1:是',
    tenant_id        bigint unsigned  default 0                                             not null comment '租户id 0:公共租户',
    version          int unsigned     default 0                                             not null comment '版本号',
    deleted          tinyint unsigned default 0                                             not null comment '是否删除 0:否 1:是',
    create_user_id   bigint unsigned  default 0                                             not null comment '创建人id',
    create_user_name varchar(32)      default ''                                            not null comment '创建人姓名',
    create_time      timestamp        default current_timestamp                             not null comment '创建时间',
    update_user_id   bigint unsigned  default 0                                             not null comment '更新人id',
    update_user_name varchar(32)      default ''                                            not null comment '更新人姓名',
    update_time      timestamp        default current_timestamp on update current_timestamp not null comment '更新时间',
    primary key (id),
    unique key uk_client_id_user_id (client_id, user_id)
) engine = InnoDB
  default charset = utf8mb4 comment '授权信息表';

-- ----------------------------
--  token信息表
-- ----------------------------
drop table if exists t_token_info;

create table t_token_info
(
    id                  bigint unsigned auto_increment                                         not null comment '主键id',
    token_value         varchar(100)     default ''                                            not null comment 'token值',
    token_type          varchar(100)     default ''                                            not null comment 'token类型 token_type=authorization_code|access_token|refresh_token|oidc_id_token|user_code|device_code',
    issued_at           timestamp        default current_timestamp                             not null comment '发放时间',
    expires_at          timestamp        default '9999-12-31 23:59:59'                         not null comment '过期时间',
    metadata            varchar(2000)    default ''                                            not null comment '元数据',
    access_token_type   varchar(100)     default ''                                            not null comment '访问token类型 token_type=access_token时有效',
    access_token_scopes varchar(1000)    default ''                                            not null comment '访问token作用域 token_type=access_token时有效',
    authorization_id    bigint unsigned  default 0                                             not null comment '授权id',
    tenant_id           bigint unsigned  default 0                                             not null comment '租户id 0:公共租户',
    version             int unsigned     default 0                                             not null comment '版本号',
    deleted             tinyint unsigned default 0                                             not null comment '是否删除 0:否 1:是',
    create_user_id      bigint unsigned  default 0                                             not null comment '创建人id',
    create_user_name    varchar(32)      default ''                                            not null comment '创建人姓名',
    create_time         timestamp        default current_timestamp                             not null comment '创建时间',
    update_user_id      bigint unsigned  default 0                                             not null comment '更新人id',
    update_user_name    varchar(32)      default ''                                            not null comment '更新人姓名',
    update_time         timestamp        default current_timestamp on update current_timestamp not null comment '更新时间',
    primary key (id),
    unique key uk_token_type_authorization_id (token_type, authorization_id)
) engine = InnoDB
  default charset = utf8mb4 comment 'token信息表';

-- ----------------------------
--  用户信息表
-- ----------------------------
drop table if exists t_user_info;

create table t_user_info
(
    id               bigint unsigned auto_increment                                         not null comment '主键id',
    username         varchar(100)     default ''                                            not null comment '用户名',
    password         varchar(100)     default ''                                            not null comment '密码',
    mobile           varchar(20)      default ''                                            not null comment '手机号',
    email            varchar(100)     default ''                                            not null comment '邮箱',
    real_name        varchar(100)     default ''                                            not null comment '真实姓名',
    nick_name        varchar(100)     default ''                                            not null comment '昵称',
    avatar           varchar(100)     default ''                                            not null comment '头像',
    language         varchar(20)      default ''                                            not null comment '语言',
    locale           varchar(20)      default ''                                            not null comment '区域',
    time_zone        varchar(20)      default ''                                            not null comment '时区',
    tenant_id        bigint unsigned  default 0                                             not null comment '租户id 0:公共租户',
    version          int unsigned     default 0                                             not null comment '版本号',
    deleted          tinyint unsigned default 0                                             not null comment '是否删除 0:否 1:是',
    create_user_id   bigint unsigned  default 0                                             not null comment '创建人id',
    create_user_name varchar(32)      default ''                                            not null comment '创建人姓名',
    create_time      timestamp        default current_timestamp                             not null comment '创建时间',
    update_user_id   bigint unsigned  default 0                                             not null comment '更新人id',
    update_user_name varchar(32)      default ''                                            not null comment '更新人姓名',
    update_time      timestamp        default current_timestamp on update current_timestamp not null comment '更新时间',
    primary key (id),
    unique key uk_username (username),
    unique key uk_mobile (mobile),
    unique key uk_email (email)
) engine = InnoDB
  default charset = utf8mb4 comment '用户信息表';

-- ----------------------------
--  角色信息表
-- ----------------------------
drop table if exists t_role_info;

create table t_role_info
(
    id               bigint unsigned auto_increment                                         not null comment '主键id',
    code             varchar(100)     default ''                                            not null comment '角色编码',
    name             varchar(100)     default ''                                            not null comment '角色名称',
    description      varchar(1000)    default ''                                            not null comment '角色描述',
    type_id          bigint unsigned  default 0                                             not null comment '角色类型id 0:公共角色类型',
    parent_id        bigint unsigned  default 0                                             not null comment '父角色id 0:无父角色',
    sort             int unsigned     default 0                                             not null comment '排序',
    tenant_id        bigint unsigned  default 0                                             not null comment '租户id 0:公共租户',
    version          int unsigned     default 0                                             not null comment '版本号',
    deleted          tinyint unsigned default 0                                             not null comment '是否删除 0:否 1:是',
    create_user_id   bigint unsigned  default 0                                             not null comment '创建人id',
    create_user_name varchar(32)      default ''                                            not null comment '创建人姓名',
    create_time      timestamp        default current_timestamp                             not null comment '创建时间',
    update_user_id   bigint unsigned  default 0                                             not null comment '更新人id',
    update_user_name varchar(32)      default ''                                            not null comment '更新人姓名',
    update_time      timestamp        default current_timestamp on update current_timestamp not null comment '更新时间',
    primary key (id),
    unique key uk_code (code),
    unique key uk_name (name)
) engine = InnoDB
  default charset = utf8mb4 comment '角色信息表';

-- ----------------------------
--  角色类型表
-- ----------------------------
drop table if exists t_role_type;

create table t_role_type
(
    id               bigint unsigned auto_increment                                         not null comment '主键id',
    code             varchar(100)     default ''                                            not null comment '角色类型编码',
    name             varchar(100)     default ''                                            not null comment '角色类型名称',
    description      varchar(1000)    default ''                                            not null comment '角色类型描述',
    tenant_id        bigint unsigned  default 0                                             not null comment '租户id 0:公共租户',
    version          int unsigned     default 0                                             not null comment '版本号',
    deleted          tinyint unsigned default 0                                             not null comment '是否删除 0:否 1:是',
    create_user_id   bigint unsigned  default 0                                             not null comment '创建人id',
    create_user_name varchar(32)      default ''                                            not null comment '创建人姓名',
    create_time      timestamp        default current_timestamp                             not null comment '创建时间',
    update_user_id   bigint unsigned  default 0                                             not null comment '更新人id',
    update_user_name varchar(32)      default ''                                            not null comment '更新人姓名',
    update_time      timestamp        default current_timestamp on update current_timestamp not null comment '更新时间',
    primary key (id),
    unique key uk_code (code),
    unique key uk_name (name)
) engine = InnoDB
  default charset = utf8mb4 comment '角色类型表';

-- ----------------------------
--  权限信息表
-- ----------------------------
drop table if exists t_permission_info;

create table t_permission_info
(
    id               bigint unsigned auto_increment                                         not null comment '主键id',
    code             varchar(100)     default ''                                            not null comment '权限编码',
    name             varchar(100)     default ''                                            not null comment '权限名称',
    description      varchar(1000)    default ''                                            not null comment '权限描述',
    type_id          bigint unsigned  default 0                                             not null comment '权限类型id 0:公共权限类型',
    parent_id        bigint unsigned  default 0                                             not null comment '父权限id 0:无父权限',
    sort             int unsigned     default 0                                             not null comment '排序',
    command          varchar(1000)    default ''                                            not null comment '命令',
    tenant_id        bigint unsigned  default 0                                             not null comment '租户id 0:公共租户',
    version          int unsigned     default 0                                             not null comment '版本号',
    deleted          tinyint unsigned default 0                                             not null comment '是否删除 0:否 1:是',
    create_user_id   bigint unsigned  default 0                                             not null comment '创建人id',
    create_user_name varchar(32)      default ''                                            not null comment '创建人姓名',
    create_time      timestamp        default current_timestamp                             not null comment '创建时间',
    update_user_id   bigint unsigned  default 0                                             not null comment '更新人id',
    update_user_name varchar(32)      default ''                                            not null comment '更新人姓名',
    update_time      timestamp        default current_timestamp on update current_timestamp not null comment '更新时间',
    primary key (id),
    unique key uk_code (code),
    unique key uk_name (name)
) engine = InnoDB
  default charset = utf8mb4 comment '权限信息表';

-- ----------------------------
--  权限类型表
-- ----------------------------
drop table if exists t_permission_type;

create table t_permission_type
(
    id               bigint unsigned auto_increment                                         not null comment '主键id',
    code             varchar(100)     default ''                                            not null comment '权限类型编码',
    name             varchar(100)     default ''                                            not null comment '权限类型名称',
    description      varchar(1000)    default ''                                            not null comment '权限类型描述',
    tenant_id        bigint unsigned  default 0                                             not null comment '租户id 0:公共租户',
    version          int unsigned     default 0                                             not null comment '版本号',
    deleted          tinyint unsigned default 0                                             not null comment '是否删除 0:否 1:是',
    create_user_id   bigint unsigned  default 0                                             not null comment '创建人id',
    create_user_name varchar(32)      default ''                                            not null comment '创建人姓名',
    create_time      timestamp        default current_timestamp                             not null comment '创建时间',
    update_user_id   bigint unsigned  default 0                                             not null comment '更新人id',
    update_user_name varchar(32)      default ''                                            not null comment '更新人姓名',
    update_time      timestamp        default current_timestamp on update current_timestamp not null comment '更新时间',
    primary key (id),
    unique key uk_code (code),
    unique key uk_name (name)
) engine = InnoDB
  default charset = utf8mb4 comment '权限类型表';

-- ----------------------------
--  组织信息表
-- ----------------------------
drop table if exists t_organization_info;

create table t_organization_info
(
    id               bigint unsigned auto_increment                                         not null comment '主键id',
    code             varchar(100)     default ''                                            not null comment '组织编码',
    name             varchar(100)     default ''                                            not null comment '组织名称',
    description      varchar(1000)    default ''                                            not null comment '组织描述',
    type_id          bigint unsigned  default 0                                             not null comment '组织类型id 0:公共组织类型',
    parent_id        bigint unsigned  default 0                                             not null comment '父组织id 0:无父组织',
    sort             int unsigned     default 0                                             not null comment '排序',
    tenant_id        bigint unsigned  default 0                                             not null comment '租户id 0:公共租户',
    version          int unsigned     default 0                                             not null comment '版本号',
    deleted          tinyint unsigned default 0                                             not null comment '是否删除 0:否 1:是',
    create_user_id   bigint unsigned  default 0                                             not null comment '创建人id',
    create_user_name varchar(32)      default ''                                            not null comment '创建人姓名',
    create_time      timestamp        default current_timestamp                             not null comment '创建时间',
    update_user_id   bigint unsigned  default 0                                             not null comment '更新人id',
    update_user_name varchar(32)      default ''                                            not null comment '更新人姓名',
    update_time      timestamp        default current_timestamp on update current_timestamp not null comment '更新时间',
    primary key (id),
    unique key uk_code (code),
    unique key uk_name (name)
) engine = InnoDB
  default charset = utf8mb4 comment '组织信息表';

-- ----------------------------
--  组织类型表
-- ----------------------------
drop table if exists t_organization_type;

create table t_organization_type
(
    id               bigint unsigned auto_increment                                         not null comment '主键id',
    code             varchar(100)     default ''                                            not null comment '组织类型编码',
    name             varchar(100)     default ''                                            not null comment '组织类型名称',
    description      varchar(1000)    default ''                                            not null comment '组织类型描述',
    tenant_id        bigint unsigned  default 0                                             not null comment '租户id 0:公共租户',
    version          int unsigned     default 0                                             not null comment '版本号',
    deleted          tinyint unsigned default 0                                             not null comment '是否删除 0:否 1:是',
    create_user_id   bigint unsigned  default 0                                             not null comment '创建人id',
    create_user_name varchar(32)      default ''                                            not null comment '创建人姓名',
    create_time      timestamp        default current_timestamp                             not null comment '创建时间',
    update_user_id   bigint unsigned  default 0                                             not null comment '更新人id',
    update_user_name varchar(32)      default ''                                            not null comment '更新人姓名',
    update_time      timestamp        default current_timestamp on update current_timestamp not null comment '更新时间',
    primary key (id),
    unique key uk_code (code),
    unique key uk_name (name)
) engine = InnoDB
  default charset = utf8mb4 comment '组织类型表';

-- ----------------------------
--  租户信息表
-- ----------------------------
drop table if exists t_tenant_info;

create table t_tenant_info
(
    id               bigint unsigned auto_increment                                         not null comment '主键id',
    code             varchar(100)     default ''                                            not null comment '租户编码',
    name             varchar(100)     default ''                                            not null comment '租户名称',
    description      varchar(1000)    default ''                                            not null comment '租户描述',
    type_id          bigint unsigned  default 0                                             not null comment '租户类型id 0:公共租户类型',
    tenant_id        bigint unsigned  default 0                                             not null comment '租户id 0:公共租户',
    version          int unsigned     default 0                                             not null comment '版本号',
    deleted          tinyint unsigned default 0                                             not null comment '是否删除 0:否 1:是',
    create_user_id   bigint unsigned  default 0                                             not null comment '创建人id',
    create_user_name varchar(32)      default ''                                            not null comment '创建人姓名',
    create_time      timestamp        default current_timestamp                             not null comment '创建时间',
    update_user_id   bigint unsigned  default 0                                             not null comment '更新人id',
    update_user_name varchar(32)      default ''                                            not null comment '更新人姓名',
    update_time      timestamp        default current_timestamp on update current_timestamp not null comment '更新时间',
    primary key (id),
    unique key uk_code (code),
    unique key uk_name (name)
) engine = InnoDB
  default charset = utf8mb4 comment '租户信息表';

-- ----------------------------
--  租户类型表
-- ----------------------------
drop table if exists t_tenant_type;

create table t_tenant_type
(
    id               bigint unsigned auto_increment                                         not null comment '主键id',
    code             varchar(100)     default ''                                            not null comment '租户类型编码',
    name             varchar(100)     default ''                                            not null comment '租户类型名称',
    description      varchar(1000)    default ''                                            not null comment '租户类型描述',
    tenant_id        bigint unsigned  default 0                                             not null comment '租户id 0:公共租户',
    version          int unsigned     default 0                                             not null comment '版本号',
    deleted          tinyint unsigned default 0                                             not null comment '是否删除 0:否 1:是',
    create_user_id   bigint unsigned  default 0                                             not null comment '创建人id',
    create_user_name varchar(32)      default ''                                            not null comment '创建人姓名',
    create_time      timestamp        default current_timestamp                             not null comment '创建时间',
    update_user_id   bigint unsigned  default 0                                             not null comment '更新人id',
    update_user_name varchar(32)      default ''                                            not null comment '更新人姓名',
    update_time      timestamp        default current_timestamp on update current_timestamp not null comment '更新时间',
    primary key (id),
    unique key uk_code (code),
    unique key uk_name (name)
) engine = InnoDB
  default charset = utf8mb4 comment '租户类型表';

-- ----------------------------
--  用户角色关系表
-- ----------------------------
drop table if exists t_user_role_rel;

create table t_user_role_rel
(
    id               bigint unsigned auto_increment                                         not null comment '主键id',
    user_id          bigint unsigned  default 0                                             not null comment '用户id',
    role_id          bigint unsigned  default 0                                             not null comment '角色id',
    default_flag     tinyint unsigned default 0                                             not null comment '是否默认 0:否 1:是',
    tenant_id        bigint unsigned  default 0                                             not null comment '租户id 0:公共租户',
    version          int unsigned     default 0                                             not null comment '版本号',
    deleted          tinyint unsigned default 0                                             not null comment '是否删除 0:否 1:是',
    create_user_id   bigint unsigned  default 0                                             not null comment '创建人id',
    create_user_name varchar(32)      default ''                                            not null comment '创建人姓名',
    create_time      timestamp        default current_timestamp                             not null comment '创建时间',
    update_user_id   bigint unsigned  default 0                                             not null comment '更新人id',
    update_user_name varchar(32)      default ''                                            not null comment '更新人姓名',
    update_time      timestamp        default current_timestamp on update current_timestamp not null comment '更新时间',
    primary key (id),
    unique key uk_user_id_role_id (user_id, role_id)
) engine = InnoDB
  default charset = utf8mb4 comment '用户角色关系表';

-- ----------------------------
--  角色权限关系表
-- ----------------------------
drop table if exists t_role_permission_rel;

create table t_role_permission_rel
(
    id               bigint unsigned auto_increment                                         not null comment '主键id',
    role_id          bigint unsigned  default 0                                             not null comment '角色id',
    permission_id    bigint unsigned  default 0                                             not null comment '权限id',
    tenant_id        bigint unsigned  default 0                                             not null comment '租户id 0:公共租户',
    version          int unsigned     default 0                                             not null comment '版本号',
    deleted          tinyint unsigned default 0                                             not null comment '是否删除 0:否 1:是',
    create_user_id   bigint unsigned  default 0                                             not null comment '创建人id',
    create_user_name varchar(32)      default ''                                            not null comment '创建人姓名',
    create_time      timestamp        default current_timestamp                             not null comment '创建时间',
    update_user_id   bigint unsigned  default 0                                             not null comment '更新人id',
    update_user_name varchar(32)      default ''                                            not null comment '更新人姓名',
    update_time      timestamp        default current_timestamp on update current_timestamp not null comment '更新时间',
    primary key (id),
    unique key uk_role_id_permission_id (role_id, permission_id)
) engine = InnoDB
  default charset = utf8mb4 comment '角色权限关系表';

-- ----------------------------
--  用户组织关系表
-- ----------------------------
drop table if exists t_user_organization_rel;

create table t_user_organization_rel
(
    id               bigint unsigned auto_increment                                         not null comment '主键id',
    user_id          bigint unsigned  default 0                                             not null comment '用户id',
    organization_id  bigint unsigned  default 0                                             not null comment '组织id',
    default_flag     tinyint unsigned default 0                                             not null comment '是否默认 0:否 1:是',
    tenant_id        bigint unsigned  default 0                                             not null comment '租户id 0:公共租户',
    version          int unsigned     default 0                                             not null comment '版本号',
    deleted          tinyint unsigned default 0                                             not null comment '是否删除 0:否 1:是',
    create_user_id   bigint unsigned  default 0                                             not null comment '创建人id',
    create_user_name varchar(32)      default ''                                            not null comment '创建人姓名',
    create_time      timestamp        default current_timestamp                             not null comment '创建时间',
    update_user_id   bigint unsigned  default 0                                             not null comment '更新人id',
    update_user_name varchar(32)      default ''                                            not null comment '更新人姓名',
    update_time      timestamp        default current_timestamp on update current_timestamp not null comment '更新时间',
    primary key (id),
    unique key uk_user_id_organization_id (user_id, organization_id)
) engine = InnoDB
  default charset = utf8mb4 comment '用户组织关系表';