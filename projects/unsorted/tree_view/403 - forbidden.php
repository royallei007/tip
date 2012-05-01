<?php
header('HTTP/1.1 403 Forbidden');
print '<?xml version="1.0" encoding="UTF-8" standalone="no" ?' . ">\n"
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
  <head>
    <title>403 - Forbidden</title>
    <link rel="stylesheet" type="text/css" href="../styles/main.css" />
    <style type="text/css">
    </style>
    <script src="http://www.google-analytics.com/urchin.js" type="text/javascript"></script>
    <script type="text/javascript">
      _uacct = "UA-939849-1";
      urchinTracker();
    </script>
  </head>
  <body>
    <h1>Filelist REST Service</h1>
    <p>The <code>path</code> you requested included a relative path:</p>
    <pre><?php print htmlspecialchars($_GET['path']) ?></pre>
  </body>
</html>
