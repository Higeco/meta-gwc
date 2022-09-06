SUMMARY = "Delivers Higeco package signing public keys to the distribution"
LICENSE = "CLOSED"

# Place new keys into the higeco-signing-keys/keys directory and they will be automatically added
SRC_URI += "file://keys/"

# Remember: to manage correctly updates, either bump the version in the filename when you change
# the contents or add and then increment the PR of the package (ie PR="r0", then "r1" ...)

inherit allarch deploy

DIRFILES = "1"
INHIBIT_DEFAULT_DEPS = "1"

SYSROOT_DIRS += "${sysconfdir}/pki"

PACKAGES =+ "${PN}-ipk ${PN}-rpm"

FILES:${PN}-rpm = "${sysconfdir}/pki/rpm-gpg"
FILES:${PN}-ipk = "${sysconfdir}/pki/ipk-gpg"

RDEPENDS:${PN}-dev = ""


# Manages for both RPM and OPKG for completeness
do_install () {
  install -d -m 0644 ${D}${sysconfdir}/pki/rpm-gpg/
  install -D -m 0644 ${S}/../keys/* ${D}${sysconfdir}/pki/rpm-gpg/

  install -d -m 0644 ${D}${sysconfdir}/pki/ipk-gpg/
  install -D -m 0644 ${S}/../keys/* ${D}${sysconfdir}/pki/ipk-gpg/
}

