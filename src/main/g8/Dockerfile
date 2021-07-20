FROM openjdk:11-jdk-slim AS build
USER root

WORKDIR /opt/build

ADD . /opt/build

#RUN apt-get update \
# && apt-get install -y curl \
# && curl -Ls https://git.io/sbt > /opt/build/sbt \
# && chmod 0755 /opt/build/sbt
RUN mkdir -p                                       /root/.sbt/launchers/1.5.4                    \
 && cp /opt/build/devOps/sbt-launch-1.5.4.jar      /root/.sbt/launchers/1.5.4/sbt-launch.jar     \
 && cp /opt/build/devOps/sbt-launch-1.5.4.jar.md5  /root/.sbt/launchers/1.5.4/sbt-launch.jar.md5 \
 && cp /opt/build/devOps/sbt                       /opt/build/sbt

RUN  /opt/build/sbt -v -213 -no-colors -batch -sbt-version 1.5.4 -J-Xmx4096M 'compile'
RUN  /opt/build/sbt -v -213 -no-colors -batch -sbt-version 1.5.4 -J-Xmx4096M 'docker:stage'

#####################################################
# Taken from Play app's build script sinec it is good standard to start with
FROM openjdk:11-jdk-slim AS persmissions
WORKDIR /opt/docker
#COPY --from=build --chown=root:root  /opt/build/target/docker/stage/opt /opt
COPY --from=build --chown=root:root  /opt/build/target/docker/stage/1/opt /opt
COPY --from=build --chown=root:root  /opt/build/target/docker/stage/2/opt /opt
USER root
RUN ["rm", "-fr", "/opt/docker/share"]
RUN ["chmod", "-R", "u=rX,g=rX", "/opt/docker"]
RUN ["chmod", "u+x,g+x", "/opt/docker/bin/$name;format="norm"$"]

#####################################################
# Taken from Play app's build script sinec it is good standard to start with
FROM openjdk:11-jre-slim AS runtime
USER root
RUN  id -u hullG82659 1>/dev/null 2>&1 \
   || ( \
        ( getent group 0 1>/dev/null 2>&1 \
   ||     ( type groupadd 1>/dev/null 2>&1 && groupadd -g 0 root || addgroup -g 0 -S root ) \
        ) \
   &&   ( type useradd 1>/dev/null 2>&1 \
          && useradd --system --create-home --uid 1001 --gid 0 hullG82659 \
          || adduser -S -u 1001 -G root hullG82659 ) \
      )
WORKDIR /opt/docker
COPY --from=persmissions --chown=hullG82659:root /opt/docker /opt/docker
RUN ["chown", "hullG82659", "/opt/docker"]
EXPOSE 9000
USER 1001:0
ENTRYPOINT ["/opt/docker/bin/$name;format="norm"$"]

CMD []
