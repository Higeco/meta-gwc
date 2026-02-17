FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI:append = "file://dnf-higeco.conf"

do_install:append() {
    # Install the custom dnf.conf file
    install -m 0644 ${WORKDIR}/dnf-higeco.conf ${D}${sysconfdir}/dnf/dnf.conf
}

