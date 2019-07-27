package flipr;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin( origins = "*" )
@RestController
@RequestMapping("/device")
public class DeviceController {
	@Autowired
	private DeviceService deviceService;

	
	@RequestMapping(value = "/all", produces = "application/JSON")
	public List<Device> getAllDevices() {
		System.out.println("getAll api");
		return deviceService.getAll();
	}

	@RequestMapping(value = "/locations/{device}/{page}", produces = "application/JSON")
	public List getLocations(@PathVariable("device") String deviceId, @PathVariable("page") Integer page) {
		if(deviceId==null)
		{
			return null;
		}
		else
		{
			return deviceService.getLocations(deviceId,page);			
		}
	}

}