# net-snmp v5.9.3
Mainstream kirkstone comes with net-snmp v5.9.1 which is affected by a known build
issue when compiling with openssl >= 3.

See here: https://github.com/net-snmp/net-snmp/issues/344

The problem is that kirkstone recipes downloads an gzip archive of the net-snmp v5.9.1 from sourceforge that doesn't contain the fix.

It should be possibile to create and applay a patch to the main version and discard this changes if not appropriate.

I took all the files from mainstream `meta-openembedded` repo with SHA: `bf4a826c7de51dcdac87f81fa2bd2301629d50db`

Link to github mirror commit: https://github.com/openembedded/meta-openembedded/commit/bf4a826c7de51dcdac87f81fa2bd2301629d50db

## BBLayer
I've created the bblayer to make it clearly what our customizations are and keep the "upstream" code unchanged.


