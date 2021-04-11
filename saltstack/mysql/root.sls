mysql_root_user:
  mysql_user.present:
    - name: root
    - password: changeme
    - host: localhost