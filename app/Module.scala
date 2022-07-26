import java.time.Clock

import com.google.inject.AbstractModule
import com.redis.RedisClient
import domain.repository.PicturePropertyRepository
import infrastructure.repository.PicturePropertyRepositoryImpl
import play.api.{Configuration, Environment}

class Module(environment: Environment,
             configuration: Configuration) extends AbstractModule {

  val redisHost: String = configuration.get[String]("mojipic.redis.host")
  val redisPort: Int = configuration.get[Int]("mojipic.redis.port")

  override def configure(): Unit = {
    bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)
    bind(classOf[PicturePropertyRepository]).to(classOf[PicturePropertyRepositoryImpl])
    bind(classOf[RedisClient]).toInstance(new RedisClient(redisHost, redisPort))
  }
}