FILESEXTRAPATHS:append := "${THISDIR}/${PN}"
SRC_URI:append = " file://dnf-higeco.conf file://higeco.repo"

do_install:append:class-target() {
    # Install the custom dnf.conf file and higeco repository
    install -m 0644 ${WORKDIR}/dnf-higeco.conf ${D}${sysconfdir}/dnf/dnf.conf
    install -d ${D}${sysconfdir}/yum.repos.d/
    install -m 0644 ${WORKDIR}/higeco.repo ${D}${sysconfdir}/yum.repos.d/higeco.repo
}

