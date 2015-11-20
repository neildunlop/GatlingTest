Gatling for Testing
===================

Execute a set of gatling tests with 'sbt test' on the command line.

Graphite for Testing
====================

Graphite is a data collection, analysis and charting tool.  We can use it to collect data from our test runner (Gatling) and plot it in realtime.
The extra bonus is that we can collect data from the host server that is running the tested application and pass that to Graphite for charting and analysis.

Graphite can be a little tricky to setup but, thankfully, we can get a pre-configured Docker image and run Graphite from there.  Graphite exposes a web interface for viewing the collected data and
another port for accepting data that should be logged, analysed and charted.

Getting the Graphite Image
==========================
See (http://michaelheap.com/easy-graphite-instances-with-docker/) for more information

To get and run a suitable docker image for Graphite, use the following command:

sudo docker run -d \
  --name graphite \
  -p 81:80 \
  -p 2003:2003 \
  -p 8125:8125/udp \
  hopsoft/graphite-statsd

This command will run the docker image of Graphite and expose the web UI on port 81 and the collector interface on port 2003.
When you execute this command the docker image will run.
To check the ports that are exposed you can execute 'sudo docker ps' which will list all the running instances and the ports they expose.  In my case, this is:

CONTAINER ID        IMAGE                      COMMAND                  CREATED             STATUS              PORTS                                                                NAMES
6003ab28c031        hopsoft/graphite-statsd    "/sbin/my_init"          3 minutes ago       Up 3 minutes        0.0.0.0:8125->8125/udp, 0.0.0.0:2003->2003/tcp, 0.0.0.0:81->80/tcp   graphite

Navigate to 'http://localhost:81' to view the Graphite UI.

Installing and Configuring Collectd
===================================
'collectd' is a linux command line tool that can be used to collect information about the CPU and Memory utilisation of a server.
We will use collectd to gather information about the performance of the host server that is running our target GTP component and get it to send that information to our Graphite server for analysis and charting.

See (https://www.digitalocean.com/community/tutorials/how-to-configure-collectd-to-gather-system-metrics-for-graphite-on-ubuntu-14-04) for more information.

