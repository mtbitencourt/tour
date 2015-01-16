package quikkoo.mt.xptotour.test;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

public class Deployments {

	public static Archive<WebArchive> forPersistence() {
		return ShrinkWrap.create(WebArchive.class, "xptotour.war")
				.addPackage("quikkoo.mt.xptotour.model")
				.addPackage("quikkoo.mt.xptotour.repository")
				.addAsResource("jpql/customer.xml")
				.addAsResource("jpql/employee.xml")
				.addAsResource("jpql/destination.xml")
				.addAsResource("jpql/trip.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsManifestResource("persistence-test.xml", "persistence.xml")
				.addAsLibraries(resolveMavenDependencies());
	}

	public static Archive<WebArchive> forController() {
		return ShrinkWrap.create(WebArchive.class, "xptotour.war")
				.addPackage("quikkoo.mt.xptotour.model")
				.addPackage("quikkoo.mt.xptotour.repository")
				.addPackage("quikkoo.mt.xptotour.controller")
				.addAsResource("jpql/customer.xml")
				.addAsResource("jpql/employee.xml")
				.addAsResource("jpql/destination.xml")
				.addAsResource("jpql/trip.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsManifestResource("persistence-test.xml", "persistence.xml")
				.addAsLibraries(resolveMavenDependencies());
	}

	private static JavaArchive[] resolveMavenDependencies() {
		return Maven.resolver()
			.loadPomFromFile("pom.xml")
			.importRuntimeDependencies()
			.resolve()
			.withTransitivity()
			.as(JavaArchive.class);
	}
}
