
# Augment/override Poky recipe to:
#  - Add patch that fixes curl build with OpenLDAP (https://github.com/curl/curl/pull/10445)
#  - Enable LDAP support for our build
#  - Fix recipe for OpenLDAP support (https://github.com/openembedded/openembedded-core/commit/a999f62f5692687a5557f7a50c7c768c50f3d7d3)

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " file://0001-Fix_ldap_build.patch "

PACKAGECONFIG = "${@bb.utils.filter('DISTRO_FEATURES', 'ipv6', d)} libidn openssl proxy random threaded-resolver verbose zlib ldap ldaps "

PACKAGECONFIG[ldap] = "--enable-ldap,--disable-ldap,openldap"
PACKAGECONFIG[ldaps] = "--enable-ldaps,--disable-ldaps,openldap"

