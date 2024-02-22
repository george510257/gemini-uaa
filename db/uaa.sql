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
-- 用户授权确认表
-- ----------------------------
drop table if exists t_oauth_approvals;

create table t_oauth_approvals
(
    id               bigint unsigned auto_increment                                         not null comment '主键id',
    user_id          bigint unsigned  default 0                                             not null comment '用户id',
    client_id        varchar(32)      default ''                                            not null comment '客户端id',
    scopes           varchar(256)     default ''                                            not null comment '授权范围',
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
    unique key uk_user_id_client_id (user_id, client_id)
) engine = InnoDB
  default charset = utf8mb4 comment '用户授权确认表';

-- ----------------------------
-- 用户认证信息表
-- ----------------------------
drop table if exists t_oauth_client_details;

create table t_oauth_client_details
(
    id                            bigint unsigned auto_increment                                         not null comment '主键id',
    client_id                     varchar(32)      default ''                                            not null comment '客户端id',
    user_id                       bigint unsigned  default 0                                             not null comment '用户id',
    grant_type                    varchar(32)      default ''                                            not null comment '授权类型',
    scopes                        varchar(256)     default ''                                            not null comment '授权范围',
    attributes                    varchar(256)     default ''                                            not null comment '客户端属性',
    authorization_code_value      varchar(256)     default ''                                            not null comment '授权码值',
    authorization_code_issued_at  timestamp        default current_timestamp                             not null comment '授权码发放时间',
    authorization_code_expires_at timestamp        default current_timestamp                             not null comment '授权码过期时间',
    authorization_code_metadata   varchar(256)     default ''                                            not null comment '授权码元数据',
    access_token_value            varchar(256)     default ''                                            not null comment '访问令牌值',
    access_token_issued_at        timestamp        default current_timestamp                             not null comment '访问令牌发放时间',
    access_token_expires_at       timestamp        default current_timestamp                             not null comment '访问令牌过期时间',
    access_token_metadata         varchar(256)     default ''                                            not null comment '访问令牌元数据',
    access_token_type             varchar(32)      default ''                                            not null comment '访问令牌类型',
    access_token_scopes           varchar(256)     default ''                                            not null comment '访问令牌范围',
    oidc_id_token_value           varchar(256)     default ''                                            not null comment 'oidc id令牌值',
    oidc_id_token_issued_at       timestamp        default current_timestamp                             not null comment 'oidc id令牌发放时间',
    oidc_id_token_expires_at      timestamp        default current_timestamp                             not null comment 'oidc id令牌过期时间',
    oidc_id_token_metadata        varchar(256)     default ''                                            not null comment 'oidc id令牌元数据',
    refresh_token_value           varchar(256)     default ''                                            not null comment '刷新令牌值',
    refresh_token_issued_at       timestamp        default current_timestamp                             not null comment '刷新令牌发放时间',
    refresh_token_expires_at      timestamp        default current_timestamp                             not null comment '刷新令牌过期时间',
    refresh_token_metadata        varchar(256)     default ''                                            not null comment '刷新令牌元数据',
    user_code_value               varchar(32)      default ''                                            not null comment '用户代码值',
    user_code_issued_at           timestamp        default current_timestamp                             not null comment '用户代码发放时间',
    user_code_expires_at          timestamp        default current_timestamp                             not null comment '用户代码过期时间',
    user_code_metadata            varchar(256)     default ''                                            not null comment '用户代码元数据',
    device_code_value             varchar(32)      default ''                                            not null comment '设备代码值',
    device_code_issued_at         timestamp        default current_timestamp                             not null comment '设备代码发放时间',
    device_code_expires_at        timestamp        default current_timestamp                             not null comment '设备代码过期时间',
    device_code_metadata          varchar(256)     default ''                                            not null comment '设备代码元数据',
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
    unique key uk_client_id_user_id (client_id, user_id)
) engine = InnoDB
  default charset = utf8mb4 comment '用户认证信息表';

-- ----------------------------
-- 客户端信息表
-- ----------------------------
drop table if exists t_oauth_client;

create table t_oauth_client
(
    id                            bigint unsigned auto_increment                                         not null comment '主键id',
    client_id                     varchar(32)      default ''                                            not null comment '客户端id',
    client_secret                 varchar(32)      default ''                                            not null comment '客户端密钥',
    client_id_issued_at           timestamp        default current_timestamp                             not null comment '客户端id发放时间',
    client_secret_issued_at       timestamp        default current_timestamp                             not null comment '客户端密钥发放时间',
    client_name                   varchar(32)      default ''                                            not null comment '客户端名称',
    client_authentication_methods varchar(32)      default ''                                            not null comment '客户端认证方法',
    grant_types                   varchar(256)     default ''                                            not null comment '授权类型',
    redirect_uris                 varchar(256)     default ''                                            not null comment '重定向uri',
    post_logout_redirect_uris     varchar(256)     default ''                                            not null comment '登出重定向uri',
    scopes                        varchar(256)     default ''                                            not null comment '授权范围',
    client_settings               varchar(256)     default ''                                            not null comment '客户端设置',
    token_settings                varchar(256)     default ''                                            not null comment '令牌设置',
    tenant_id                     bigint unsigned  default 0                                             not null comment '租户id 0:公共租户',
    version                       int unsigned     default 0                                             not null comment '版本号',
    deleted                       tinyint unsigned default 0                                             not null comment '是否删除 0:否 1:是',
    create_user_id                bigint unsigned  default 0                                             not null comment '创建人id',
    create_user_name              varchar(32)      default ''                                            not null comment '创建人姓名',
    create_time                   timestamp        default current_timestamp                             not null comment '创建时间',
    update_user_id                bigint unsigned  default 0                                             not null comment '更新人id',
    update_user_name              varchar(32)      default ''                                            not null comment '更新人姓名',
    update_time                   timestamp        default current_timestamp on update current_timestamp not null comment '更新时间',
    primary key (id)
) engine = InnoDB
  default charset = utf8mb4 comment '客户端信息表';